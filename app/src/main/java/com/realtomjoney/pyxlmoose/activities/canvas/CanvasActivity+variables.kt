package com.realtomjoney.pyxlmoose.activities.canvas

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Menu
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding
import com.realtomjoney.pyxlmoose.fragments.brushes.BrushesFragment
import com.realtomjoney.pyxlmoose.fragments.canvas.CanvasFragment
import com.realtomjoney.pyxlmoose.fragments.colorpalettes.ColorPalettesFragment
import com.realtomjoney.pyxlmoose.fragments.colorpicker.ColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.filters.FiltersFragment
import com.realtomjoney.pyxlmoose.fragments.findandreplace.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.fragments.newcolorpalette.NewColorPaletteFragment
import com.realtomjoney.pyxlmoose.fragments.outercanvas.OuterCanvasFragment
import com.realtomjoney.pyxlmoose.fragments.tools.ToolsFragment
import com.realtomjoney.pyxlmoose.models.PixelArt

lateinit var binding: ActivityCanvasBinding
var index: Int? = null

var primaryColor: Int = Color.BLACK
var secondaryColor: Int = Color.BLUE
var spanCount = 5
var isPrimaryColorSelected = true

var isSelected = false
var background: Drawable? = null

var currentFragmentInstance: Fragment? = null

lateinit var currentPixelArtObj: PixelArt

enum class Tools {
    PENCIL_TOOL,
    FILL_TOOL,
    HORIZONTAL_MIRROR_TOOL,
    VERTICAL_MIRROR_TOOL,
    LINE_TOOL,
    RECTANGLE_TOOL,
    OUTLINED_RECTANGLE_TOOL,
    DARKEN_TOOL,
    LIGHTEN_TOOL,
    COLOR_PICKER_TOOL,
    ERASE_TOOL
}

var currentTool: Tools = Tools.PENCIL_TOOL

var saved = true

lateinit var colorPickerFragmentInstance: ColorPickerFragment
lateinit var outerCanvasInstance: OuterCanvasFragment
lateinit var findAndReplaceFragmentInstance: FindAndReplaceFragment
lateinit var newColorPaletteFragmentInstance: NewColorPaletteFragment

lateinit var menu: Menu

var toolsFragmentInstance: ToolsFragment? = null
var filtersFragmentInstance: FiltersFragment? = null
var colorPalettesFragmentInstance: ColorPalettesFragment? = null
var brushesFragmentInstance: BrushesFragment? = null

var lineMode_hasLetGo = false
var rectangleMode_hasLetGo = false

var projectTitle: String? = null

lateinit var sharedPreferenceObject: SharedPreferences
