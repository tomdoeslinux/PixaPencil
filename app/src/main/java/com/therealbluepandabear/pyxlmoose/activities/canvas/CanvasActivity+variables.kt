package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Menu
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pyxlmoose.algorithms.SprayAlgorithm
import com.therealbluepandabear.pyxlmoose.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pyxlmoose.fragments.filters.FiltersFragment
import com.therealbluepandabear.pyxlmoose.fragments.findandreplace.FindAndReplaceFragment
import com.therealbluepandabear.pyxlmoose.fragments.newcolorpalette.NewColorPaletteFragment
import com.therealbluepandabear.pyxlmoose.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pyxlmoose.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.therealbluepandabear.pyxlmoose.fragments.tools.ToolsFragment
import com.therealbluepandabear.pyxlmoose.models.PixelArt
import com.therealbluepandabear.pyxlmoose.utility.IntConstants

lateinit var binding: ActivityCanvasBinding
var index: Int? = null

var primaryColor: Int = Color.BLACK
var secondaryColor: Int = Color.BLUE
var width = IntConstants.DefaultCanvasWidthHeight
var height = IntConstants.DefaultCanvasWidthHeight
var isPrimaryColorSelected = true

var isSelected = false
var background: Drawable? = null

var currentFragmentInstance: Fragment? = null

lateinit var currentPixelArtObj: PixelArt

var currentTool: Tools = Tools.PencilTool

var saved = true

lateinit var colorPickerFragmentInstance: ColorPickerFragment
lateinit var outerCanvasInstance: OuterCanvasFragment
lateinit var findAndReplaceFragmentInstance: FindAndReplaceFragment
lateinit var newColorPaletteFragmentInstance: NewColorPaletteFragment
lateinit var sprayToolSettingsFragmentInstance: SprayToolSettingsFragment

lateinit var menu: Menu

var toolsFragmentInstance: ToolsFragment? = null
var filtersFragmentInstance: FiltersFragment? = null
var colorPalettesFragmentInstance: ColorPalettesFragment? = null
var brushesFragmentInstance: BrushesFragment? = null

var lineMode_hasLetGo = false
var rectangleMode_hasLetGo = false
var circleMode_hasLetGo = false

var projectTitle: String? = null

lateinit var sharedPreferenceObject: SharedPreferences

lateinit var sprayAlgorithmInstance: SprayAlgorithm
var sprayAlgorithmInstanceInitialized = ::sprayAlgorithmInstance.isInitialized

var previewColorToFind: Int? = null
var previewColorToReplace: Int? = null

var prevOrientation: Int = 0
var prevBitmapStr: String = ""

lateinit var primaryAlgorithmInfoParameter: AlgorithmInfoParameter
val primaryAlgorithmInfoParameterInitialized = ::primaryAlgorithmInfoParameter.isInitialized


