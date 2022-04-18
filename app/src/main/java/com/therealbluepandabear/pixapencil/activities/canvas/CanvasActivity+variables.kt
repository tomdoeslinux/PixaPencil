package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Menu
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.findandreplace.FindAndReplaceFragment
import com.therealbluepandabear.pixapencil.fragments.newcolorpalette.NewColorPaletteFragment
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.IntConstants

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
var prevBitmapStr: String? = null
var prevPrimaryColor: Int? = null
var prevSecondaryColor: Int? = null
var prevTool: String? = null
var prevBrush: String? = null
var prevTab: Int = 0
var prevUndoToolbarButtonDisabledEnabledState: Boolean = false // false means it's disabled
var prevRedoToolbarButtonDisabledEnabledState: Boolean = false
var prevUndoStackJsonStr: String? = null
var prevRedoStackJsonStr: String? = null

var currentTab = 0

lateinit var primaryAlgorithmInfoParameter: AlgorithmInfoParameter
val primaryAlgorithmInfoParameterInitialized = ::primaryAlgorithmInfoParameter.isInitialized

var selectedColorPaletteIndex: Int = 0
