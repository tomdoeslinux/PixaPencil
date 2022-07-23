package com.therealbluepandabear.pixapencil.activities.canvas

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.root.extendedOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.root.onCreate
import com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed.extendedOnDoneButtonPressed
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.extendedOnPixelTapped
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.*
import com.therealbluepandabear.pixapencil.activities.canvas.viewmodel.CanvasActivityViewModel
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pixapencil.listeners.*
import com.therealbluepandabear.pixapencil.models.*
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants


var selectedColorPaletteIndex: Int = 0

class CanvasActivity :
    AppCompatActivity(),
    CanvasFragmentListener,
    ColorPaletteColorPickerListener,
    ColorPickerFragmentListener,
    ReplaceColorFragmentListener,
    ToolsFragmentListener,
    FiltersFragmentListener,
    ColorPalettesFragmentListener,
    NewColorPaletteFragmentListener,
    BrushesFragmentListener,
    SprayToolSettingsFragmentListener,
    DitherToolSettingsFragmentListener {

    lateinit var binding: ActivityCanvasBinding

    val viewModel: CanvasActivityViewModel by viewModels()

    var index: Int = -1

    var projectTitle: String = ""
    var width: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    var height: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    var uri: Uri? = null

    var background: Drawable? = null

    lateinit var menu: Menu

    var shapeOrigin: Coordinates? = null
    var shapeHasLetGo = false
    var firstShapeDrawn = false

    lateinit var sharedPreferenceObject: SharedPreferences

    lateinit var sprayAlgorithmInstance: SprayAlgorithm
    var sprayAlgorithmInstanceInitialized = ::sprayAlgorithmInstance.isInitialized

    lateinit var primaryAlgorithmInfoParameter: AlgorithmInfoParameter
    val primaryAlgorithmInfoParameterInitialized = ::primaryAlgorithmInfoParameter.isInitialized

    var shadingToolMode = StringConstants.ShadingToolModes.LIGHTEN_SHADING_TOOL_MODE

    var sharedPreferenceShowSprayToolTip = true
    var sharedPreferenceShowShadingToolTip = true
    var sharedPreferenceShowDitherToolTip = true

    inner class CanvasCommandsHelper(val baseReference: CanvasActivity = this@CanvasActivity)

    val canvasCommandsHelperInstance: CanvasCommandsHelper = CanvasCommandsHelper()

    lateinit var adapter: ColorPaletteColorPickerAdapter

    var spotLightInProgress: Boolean = false

    var originalX: Float? = null
    var originalY: Float? = null

    var dX = 0f
    var dY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()

        binding.root.setOnTouchListener { _, motionEvent ->
            val hitRect = Rect()
            binding.activityCanvasCardView.getHitRect(hitRect)

            if (hitRect.contains(motionEvent.rawX.toInt(), motionEvent.rawY.toInt())) {
                binding.activityCanvasPixelGridView.onTouchEvent(motionEvent)
            }
            true
        }
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
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

    override fun onColorTapped(colorTapped: Int) {
        extendedOnColorTapped(colorTapped)
    }

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        extendedOnColorLongTapped(colorPalette, colorIndex)
    }

    override fun onColorAdded(colorPalette: ColorPalette) {
        extendedOnAddColorTapped(colorPalette)
    }

    override fun onDoneButtonPressed(selectedColor: Int, colorPalette: ColorPalette?) {
        extendedOnDoneButtonPressed(selectedColor, colorPalette)
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

    override fun onDitherBrushTapped(ditherBrush: DitherBrush) {
        viewModel.currentDitherBrush = ditherBrush
        supportFragmentManager.popBackStackImmediate()

        binding.root.post {
            judgeUndoRedoStacks()
        }
    }
}
