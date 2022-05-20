package com.therealbluepandabear.pixapencil.fragments.findandreplace

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.FragmentFindAndReplaceBinding
import com.therealbluepandabear.pixapencil.extensions.replacePixelsByColor
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.listeners.FindAndReplaceFragmentListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.utility.BitmapUtilities

class FindAndReplaceFragment : Fragment(), ActivityFragment {
    private var colorToFind: Int? = null
    private var colorToReplace: Int? = null

    private lateinit var paramCanvasColors: List<Int>
    private lateinit var paramTransparentBitmapSource: Bitmap
    private lateinit var paramPixelGridViewBitmapSource: Bitmap
    private var selectedColorPaletteIndex: Int = 0

    override val title: String by lazy { getString(R.string.fragment_find_and_replace_title_in_code_str) }

    fun setParams(
        paramCanvasColors: List<Int>,
        paramTransparentBitmapSource: Bitmap,
        paramPixelGridViewBitmapSource: Bitmap,
        selectedColorPaletteIndex: Int) {
        this.paramCanvasColors = paramCanvasColors
        this.paramTransparentBitmapSource = paramTransparentBitmapSource
        this.paramPixelGridViewBitmapSource = paramPixelGridViewBitmapSource
        this.selectedColorPaletteIndex = selectedColorPaletteIndex
    }

    private fun setup() {
        setupCanvasColorsRecyclerView()
        setupAvailableColorsRecyclerView()
        setOnClickListeners()
        setupPreview()
    }

    private fun setupPreview() {
        val paramTransparentBitmapSourceCopy = paramTransparentBitmapSource.copy(paramTransparentBitmapSource.config, paramTransparentBitmapSource.isMutable)
        val paramPixelGridViewBitmapSourceCopy = paramPixelGridViewBitmapSource.copy(paramPixelGridViewBitmapSource.config, paramPixelGridViewBitmapSource.isMutable)

        val bitmap = BitmapUtilities.overlay(paramTransparentBitmapSourceCopy, paramPixelGridViewBitmapSourceCopy)
        binding.fragmentFindAndReplaceOldPreview.setImageBitmap(bitmap)
        binding.fragmentFindAndReplaceNewPreview.setImageBitmap(bitmap)
    }

    private fun setupCanvasColorsRecyclerView() {
        binding.apply {
            fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager =
                LinearLayoutManager(this@FindAndReplaceFragment.requireContext()).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }

            fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(
                ColorPalette(null, JsonConverter.convertListToJsonString(paramCanvasColors)),
                ColorsToFindCaller(binding))
        }
    }

    private fun setupAvailableColorsRecyclerView() {
        binding.apply {
            fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager =
                LinearLayoutManager(this@FindAndReplaceFragment.requireContext()).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }

            val copiedPaletteRemoveLast = AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData()[selectedColorPaletteIndex]
            val copiedPaletteData = JsonConverter.convertJsonStringToListOfInt(copiedPaletteRemoveLast.colorPaletteColorData).toMutableList()
            copiedPaletteData.removeLast()

            fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(
                copiedPaletteData,
                ColorsToReplaceCaller(this),
            )

        }
    }

    private fun setOnClickListeners() {
        binding.fragmentFindAndReplaceDoneButton.setOnClickListener {
            caller.onDoneButtonPressed(colorToFind, colorToReplace)
        }
    }

    inner class ColorsToFindCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) {
            colorToFind = colorTapped

            if (colorToReplace != null) {
                val bitmap = paramPixelGridViewBitmapSource.copy(paramPixelGridViewBitmapSource.config, paramPixelGridViewBitmapSource.isMutable)
                bitmap.replacePixelsByColor(colorToFind!!, colorToReplace!!)

                val finalBitmap = BitmapUtilities.overlay(paramTransparentBitmapSource, bitmap)
                binding.fragmentFindAndReplaceNewPreview.setImageBitmap(finalBitmap)
            }
        }
        override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) { }
        override fun onColorAdded(colorPalette: ColorPalette) { }
    }

    inner class ColorsToReplaceCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) {
            colorToReplace = colorTapped

            if (colorToFind != null && colorToReplace != null) {
                val bitmap = paramPixelGridViewBitmapSource.copy(paramPixelGridViewBitmapSource.config, paramPixelGridViewBitmapSource.isMutable)
                bitmap.replacePixelsByColor(colorToFind!!, colorToReplace!!)

                val finalBitmap = BitmapUtilities.overlay(paramTransparentBitmapSource, bitmap)
                binding.fragmentFindAndReplaceNewPreview.setImageBitmap(finalBitmap)
            }
        }
        override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) { }
        override fun onColorAdded(colorPalette: ColorPalette) { }
    }

    companion object {
        fun newInstance(
            paramCanvasColors: List<Int>,
            paramTransparentBitmapSource: Bitmap,
            paramPixelGridViewBitmapSource: Bitmap,
            selectedColorPaletteIndex: Int): FindAndReplaceFragment {
            val fragment = FindAndReplaceFragment()
            fragment.setParams(paramCanvasColors, paramTransparentBitmapSource, paramPixelGridViewBitmapSource, selectedColorPaletteIndex)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
        requireActivity().title = this.title
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