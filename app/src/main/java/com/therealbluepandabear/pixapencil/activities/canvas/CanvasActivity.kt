package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.extendedOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.onCreate
import com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed.extendedOnDoneButtonPressed
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.extendedOnCreateOptionsMenu
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.extendedOnOptionsItemSelected
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.extendedOnPixelTapped
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.*
import com.therealbluepandabear.pixapencil.activities.canvas.viewmodel.CanvasActivityViewModel
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
import com.therealbluepandabear.pixapencil.listeners.*
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.FileHelperUtilities
import com.therealbluepandabear.pixapencil.utility.IntConstants

lateinit var binding: ActivityCanvasBinding

class CanvasActivity :
    AppCompatActivity(),
    CanvasFragmentListener,
    ColorPaletteColorPickerListener,
    ColorPickerFragmentListener,
    FindAndReplaceFragmentListener,
    ToolsFragmentListener,
    FiltersFragmentListener,
    ColorPalettesFragmentListener,
    NewColorPaletteFragmentListener,
    BrushesFragmentListener,
    SprayToolSettingsFragmentListener {

    var previousView: View? = null
    var projectTitle: String? = null

    lateinit var outerCanvasInstance: OuterCanvasFragment

    val viewModel: CanvasActivityViewModel by viewModels()

    var index: Int? = null

    var primaryColor: Int = Color.BLACK
    var secondaryColor: Int = Color.BLUE

    var width = IntConstants.DefaultCanvasWidthHeight
    var height = IntConstants.DefaultCanvasWidthHeight

    var isPrimaryColorSelected = true

    var isSelected = false
    var background: Drawable? = null

    var currentTool: Tool = Tool.defaultTool

    var saved = true

    lateinit var menu: Menu

    var toolsFragmentInstance: ToolsFragment? = null
    var filtersFragmentInstance: FiltersFragment? = null
    var colorPalettesFragmentInstance: ColorPalettesFragment? = null
    var brushesFragmentInstance: BrushesFragment? = null

    var lineModeHasLetGo = false
    var rectangleModeHasLetGo = false
    var circleModeHasLetGo = false

    lateinit var sharedPreferenceObject: SharedPreferences

    lateinit var sprayAlgorithmInstance: SprayAlgorithm
    var sprayAlgorithmInstanceInitialized = ::sprayAlgorithmInstance.isInitialized

    var prevOrientation: Int = 0
    var prevBitmapFilePathStr: String? = null
    var prevPrimaryColor: Int? = null
    var prevSecondaryColor: Int? = null
    var prevToolStr: String? = null
    var prevBrushStr: String? = null
    var prevTab: Int = 0
    var prevUndoToolbarButtonDisabledEnabledState: Boolean = false // false means it's disabled
    var prevRedoToolbarButtonDisabledEnabledState: Boolean = false
    var prevSymmetryModeStr: String? = null
    var prevRotation: Int = 0

    var currentTab = 0

    lateinit var primaryAlgorithmInfoParameter: AlgorithmInfoParameter
    val primaryAlgorithmInfoParameterInitialized = ::primaryAlgorithmInfoParameter.isInitialized

    var selectedColorPaletteIndex: Int = 0

    var shadingToolMode = "Lighten"

    var sharedPreferenceShowSprayToolTip = true
    var sharedPreferenceShowShadingToolTip = true

    inner class CanvasCommandsHelper(val baseReference: CanvasActivity = this@CanvasActivity)

    val canvasCommandsHelperInstance: CanvasCommandsHelper = CanvasCommandsHelper()

    val colorPaletteColorPickerData = mutableListOf<Int>()
    lateinit var adapter: ColorPaletteColorPickerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        configureSavedInstanceState(savedInstanceState)
    }

    var replacedBMP = false


    override fun onStart() {
        super.onStart()
        savePrevOrientationInfo()

        val viewTemp = binding.activityCanvasOuterCanvasFragmentHost

        viewTemp.viewTreeObserver.addOnGlobalLayoutListener {
            if (viewTemp.isVisible && replacedBMP && prevOrientation != resources.configuration.orientation) {
                if (prevBitmapFilePathStr != null) {
                    val fileHelperUtilitiesInstance = FileHelperUtilities.createInstance(this)
                    val convertedBMP = fileHelperUtilitiesInstance.openBitmapFromInternalStorage(prevBitmapFilePathStr!!)

                    pixelGridViewInstance.replaceBitmap(convertedBMP)
                    fileHelperUtilitiesInstance.deleteBitmapFromInternalStorage(prevBitmapFilePathStr!!)
                    replacedBMP = false

                    prevOrientation = resources.configuration.orientation
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return extendedOnCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return extendedOnOptionsItemSelected(item)
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        extendedOnSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onViewLoaded() {
        extendedOnViewLoaded()
    }

    override fun onPixelTapped(coordinatesTapped: Coordinates) {
        extendedOnPixelTapped(coordinatesTapped)
    }

    override fun onActionUp() {
        extendedOnActionUp()
    }

    override fun dispatchTouchEvent() {
        if (viewModel.currentBitmapAction == null) {
            viewModel.currentBitmapAction = BitmapAction(mutableListOf())
        }
    }

    override fun onColorTapped(colorTapped: Int, view: View) {
        extendedOnColorTapped(colorTapped, view)
    }

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        extendedOnColorLongTapped(colorPalette, colorIndex)
    }

    override fun onColorAdded(colorPalette: ColorPalette) {
        extendedOnAddColorTapped(colorPalette)
    }

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) {
        extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)
    }

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
        extendedOnDoneButtonPressed(colorToFind, colorToReplace)
    }

    override fun onBackPressed() {
        extendedOnBackPressed()
    }

    override fun onToolTapped(toolName: String) {
        extendedOnToolTapped(toolName)
    }

    override fun onToolLongTapped(toolName: String) {
        extendedOnToolLongTapped(toolName)
    }

    override fun onFilterTapped(filterName: String) {
        extendedOnFilterTapped(filterName)
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteTapped(selectedColorPalette)
    }

    override fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteLongTapped(selectedColorPalette)
    }

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
        extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)
    }

    override fun onBrushTapped(selectedBrush: Brush) {
        extendedOnBrushTapped(selectedBrush)
    }

    override fun onDoneButtonPressed(radius: String, strength: String) {
        extendedOnDoneButtonPressed(radius, strength)
    }
}
