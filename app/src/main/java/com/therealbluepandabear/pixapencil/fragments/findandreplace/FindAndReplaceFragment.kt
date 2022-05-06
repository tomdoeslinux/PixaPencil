package com.therealbluepandabear.pixapencil.fragments.findandreplace

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.FragmentFindAndReplaceBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.listeners.FindAndReplaceFragmentListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FindAndReplaceFragment : Fragment() {
    private var colorToFind: Int? = null
    private var colorToReplace: Int? = null

    private val currentActivityInstance = this.activity

    private var lock = true

    private lateinit var paramCanvasColors: List<Int>
    private lateinit var paramBitmapSource: Bitmap
    private var selectedColorPaletteIndex: Int = 0

    fun setParams(
        paramCanvasColors: List<Int>,
        paramBitmapSource: Bitmap,
        selectedColorPaletteIndex: Int) {
        this.paramCanvasColors = paramCanvasColors
        this.paramBitmapSource = paramBitmapSource
        this.selectedColorPaletteIndex = selectedColorPaletteIndex
    }

    private fun setup() {
        setupCanvasColorsRecyclerView()
        setupAvailableColorsRecyclerView()
        setOnClickListeners()
        setupPreview()

        lifecycleScope.launch {
            delay(20)
            lock = false
        }
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
            fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(
                AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData()[selectedColorPaletteIndex], FragmentFindAndReplaceAvailableColorsRecyclerView(binding), false
            )

            fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager =
                LinearLayoutManager(currentActivityInstance).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
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
        fun newInstance(
            paramCanvasColors: List<Int>,
            paramBitmapSource: Bitmap,
            selectedColorPaletteIndex: Int): FindAndReplaceFragment {
            val fragment = FindAndReplaceFragment()
            fragment.setParams(paramCanvasColors, paramBitmapSource, selectedColorPaletteIndex)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
        requireActivity().title = getString(R.string.fragment_find_and_replace_title_in_code_str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentFindAndReplaceBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}