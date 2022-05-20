package com.therealbluepandabear.pixapencil.activities.canvas

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Menu
import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.IntConstants

@SuppressLint("StaticFieldLeak")
lateinit var binding: ActivityCanvasBinding
var index: Int? = null

var primaryColor: Int = Color.BLACK
var secondaryColor: Int = Color.BLUE

var width = IntConstants.DefaultCanvasWidthHeight
var height = IntConstants.DefaultCanvasWidthHeight

var isPrimaryColorSelected = true

var isSelected = false
var background: Drawable? = null

lateinit var currentPixelArtObj: PixelArt

var currentTool: Tool = Tool.defaultTool

var saved = true

lateinit var menu: Menu

var toolsFragmentInstance: ToolsFragment? = null
var filtersFragmentInstance: FiltersFragment? = null
var colorPalettesFragmentInstance: ColorPalettesFragment? = null
var brushesFragmentInstance: BrushesFragment? = null

var lineMode_hasLetGo = false
var rectangleMode_hasLetGo = false
var circleMode_hasLetGo = false

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