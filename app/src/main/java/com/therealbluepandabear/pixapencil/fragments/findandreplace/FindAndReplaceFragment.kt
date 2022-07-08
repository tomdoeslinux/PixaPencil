package com.therealbluepandabear.pixapencil.fragments.findandreplace

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.FragmentFindAndReplaceBinding
import com.therealbluepandabear.pixapencil.extensions.*
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.listeners.FindAndReplaceFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import java.util.ArrayList

class FindAndReplaceFragment : Fragment(), ActivityFragment {
    private var _binding: FragmentFindAndReplaceBinding? = null

    private val binding get(): FragmentFindAndReplaceBinding {
        return _binding!!
    }

    private lateinit var caller: FindAndReplaceFragmentListener

    override val title: String by lazy { getString(R.string.fragment_find_and_replace_title_in_code_str) }

    private var colorToFind: Int? = null
    private var colorToReplace: Int? = null

    private lateinit var paramCanvasColors: List<Int>
    private lateinit var paramTransparentBitmapSource: Bitmap
    private lateinit var paramPixelGridViewBitmapSource: Bitmap
    private var paramSelectedColorPaletteIndex: Int = 0
    private var paramScaledWidth: Int = 500
    private var paramScaledHeight: Int = 500

    fun setParams(
        paramCanvasColors: List<Int>,
        paramTransparentBitmapSource: Bitmap,
        paramPixelGridViewBitmapSource: Bitmap,
        paramSelectedColorPaletteIndex: Int,
        paramScaledWidth: Int,
        paramScaledHeight: Int) {
        this.paramCanvasColors = paramCanvasColors
        this.paramTransparentBitmapSource = paramTransparentBitmapSource
        this.paramPixelGridViewBitmapSource = paramPixelGridViewBitmapSource
        this.paramSelectedColorPaletteIndex = paramSelectedColorPaletteIndex
        this.paramScaledWidth = paramScaledWidth
        this.paramScaledHeight = paramScaledHeight
    }

    private fun setup() {
        setupCanvasColorsRecyclerView()
        setupAvailableColorsRecyclerView()
        setOnClickListeners()
        setupPreview()
    }

    private fun setupPreview() {
        val bitmap = Bitmap.createScaledBitmap(paramTransparentBitmapSource.clone().overlay(paramPixelGridViewBitmapSource.clone()), paramScaledWidth, paramScaledHeight, false)
        binding.fragmentFindAndReplaceOldPreview.setImageBitmap(bitmap)
        binding.fragmentFindAndReplaceNewPreview.setImageBitmap(bitmap)
    }

    private fun setupCanvasColorsRecyclerView() {
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager =
            LinearLayoutManager(this@FindAndReplaceFragment.requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }

        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(paramCanvasColors, ColorsToFindCaller(binding))
    }

    private fun findAndReplace() {
        val bitmap = paramPixelGridViewBitmapSource.clone()
        bitmap.replacePixelsByColor(colorToFind!!, colorToReplace!!)

        val finalBitmap = Bitmap.createScaledBitmap(paramTransparentBitmapSource.clone().overlay(bitmap), paramScaledWidth, paramScaledHeight, false)
        binding.fragmentFindAndReplaceNewPreview.setImageBitmap(finalBitmap)
    }

    private fun setupAvailableColorsRecyclerView() {
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager =
            LinearLayoutManager(this@FindAndReplaceFragment.requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(
            AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData()[paramSelectedColorPaletteIndex].getFindAndReplaceCompatibleColorPaletteColorData(),
            ColorsToReplaceCaller(binding),
        )
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
                findAndReplace()
            }
        }
    }

    inner class ColorsToReplaceCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) {
            colorToReplace = colorTapped

            if (colorToFind != null && colorToReplace != null) {
                findAndReplace()
            }
        }
    }

    companion object {
        fun newInstance(
            paramCanvasColors: List<Int>,
            paramTransparentBitmapSource: Bitmap,
            paramPixelGridViewBitmapSource: Bitmap,
            paramSelectedColorPaletteIndex: Int,
            paramScaledWidth: Int,
            paramScaledHeight: Int): FindAndReplaceFragment {
            val fragment = FindAndReplaceFragment()
            fragment.setParams(paramCanvasColors, paramTransparentBitmapSource, paramPixelGridViewBitmapSource, paramSelectedColorPaletteIndex, paramScaledWidth, paramScaledHeight)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFindAndReplaceBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            paramCanvasColors = savedInstanceState.getIntegerArrayList(StringConstants.Identifiers.PREV_COLORS_TO_FIND_BUNDLE_IDENTIFIER)!!.toList()
            paramTransparentBitmapSource = BitmapConverter.convertStringToBitmap(savedInstanceState.getString(
                StringConstants.Identifiers.PREV_TRANSPARENT_BITMAP_SOURCE_BUNDLE_IDENTIFIER)!!)!!.createMutableClone()
            paramPixelGridViewBitmapSource = BitmapConverter.convertStringToBitmap(savedInstanceState.getString(
                StringConstants.Identifiers.PREV_PIXEL_GRID_VIEW_BITMAP_SOURCE_BUNDLE_IDENTIFIER)!!)!!.createMutableClone()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(
            StringConstants.Identifiers.PREV_COLORS_TO_FIND_BUNDLE_IDENTIFIER,
            paramCanvasColors as ArrayList<Int>
        )

        outState.putString(
            StringConstants.Identifiers.PREV_TRANSPARENT_BITMAP_SOURCE_BUNDLE_IDENTIFIER,
            BitmapConverter.convertBitmapToString(paramTransparentBitmapSource))
        outState.putString(
            StringConstants.Identifiers.PREV_PIXEL_GRID_VIEW_BITMAP_SOURCE_BUNDLE_IDENTIFIER,
            BitmapConverter.convertBitmapToString(paramPixelGridViewBitmapSource))

        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}