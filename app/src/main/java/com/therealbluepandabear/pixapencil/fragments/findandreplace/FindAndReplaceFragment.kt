package com.therealbluepandabear.pixapencil.fragments.findandreplace

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.ColorDatabase
import com.therealbluepandabear.pixapencil.databinding.FragmentFindAndReplaceBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.listeners.FindAndReplaceFragmentListener
import com.therealbluepandabear.pixapencil.models.ColorPalette


class FindAndReplaceFragment : Fragment() {
    private var colorToFind: Int? = null
    private var colorToReplace: Int? = null

    private val currentActivityInstance = this.activity

    private var lock = true

    private lateinit var paramCanvasColors: List<Int>
    private lateinit var paramBitmapSource: Bitmap

    fun setParams(paramCanvasColors: List<Int>, paramBitmapSource: Bitmap) {
        this.paramCanvasColors = paramCanvasColors
        this.paramBitmapSource = paramBitmapSource
    }

    private fun setup() {
        setupCanvasColorsRecyclerView()
        setupAvailableColorsRecyclerView()
        setOnClickListeners()
        setupPreview()

        val h = Handler(Looper.getMainLooper())
        h.postDelayed( {
            lock = false
        }, 20)
    }

    private fun setupPreview() {
        binding.fragmentFindAndReplaceOldPreview.setImageBitmap(paramBitmapSource)
        binding.fragmentFindAndReplaceNewPreview.setImageBitmap(paramBitmapSource)
    }

    private fun setupCanvasColorsRecyclerView() {
        binding.apply {
            fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager =
                LinearLayoutManager(currentActivityInstance).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(
                ColorPalette(
                    null,
                    JsonConverter.convertListToJsonString(paramCanvasColors)
                ), FragmentFindAndReplaceCanvasColorsCaller(binding), false
            )
        }
    }

    private fun setupAvailableColorsRecyclerView() {
        binding.apply {
            fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager =
                LinearLayoutManager(currentActivityInstance).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(
                ColorPalette(
                    null,
                    JsonConverter.convertListToJsonString(ColorDatabase.toList())
                ), FragmentFindAndReplaceAvailableColorsRecyclerView(binding), false
            )
        }
    }

    private fun setOnClickListeners() {
        binding.fragmentFindAndReplaceDoneButton.setOnClickListener {
            caller.onDoneButtonPressed(colorToFind, colorToReplace)
            lock = true
        }
    }

    inner class FragmentFindAndReplaceCanvasColorsCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) {
            if (!lock) {
                colorToFind = colorTapped

                val bmp = caller.onColorToFindTapped(paramBitmapSource, colorTapped)
                binding.fragmentFindAndReplaceNewPreview.setImageBitmap(bmp)
            }
        }
        override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) { }
        override fun onColorAdded(colorPalette: ColorPalette) { }
    }

    inner class FragmentFindAndReplaceAvailableColorsRecyclerView(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) {
            if (!lock) {
                colorToReplace = colorTapped

                val bmp = caller.onColorToReplaceTapped(paramBitmapSource, colorTapped)
                binding.fragmentFindAndReplaceNewPreview.setImageBitmap(bmp)
            }
        }
        override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) { }
        override fun onColorAdded(colorPalette: ColorPalette) { }
    }

    companion object {
        fun newInstance(paramCanvasColors: List<Int>, paramBitmapSource: Bitmap): FindAndReplaceFragment {
            val fragment = FindAndReplaceFragment()
            fragment.setParams(paramCanvasColors, paramBitmapSource)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentFindAndReplaceBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}