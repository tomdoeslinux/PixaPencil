package com.realtomjoney.pyxlmoose.fragments.tools

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun ToolsFragment.getSelectedStateListPairData() = Pair(AppCompatResources.getColorStateList(context!!, android.R.color.holo_blue_dark), ContextCompat.getColorStateList(requireContext(), R.color.white))
fun ToolsFragment.getUnselectedStateListPairData() = Pair(AppCompatResources.getColorStateList(context!!, android.R.color.transparent), ContextCompat.getColorStateList(requireContext(), android.R.color.holo_blue_dark))

fun ToolsFragment.setColorFor(it: View) {
    it.backgroundTintList = getSelectedStateListPairData().first
    (it as FloatingActionButton).supportImageTintList = getSelectedStateListPairData().second
}

fun ToolsFragment.unsetColorFor(it: View) {
    it.backgroundTintList = getUnselectedStateListPairData().first
    (it as FloatingActionButton).supportImageTintList =  getUnselectedStateListPairData().second
}

fun ToolsFragment.onOptionTapped(it: View) {
    currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
    setColorFor(it)
    currentlySelectedFAB = it as FloatingActionButton
}

var currentlySelectedFAB: FloatingActionButton? = null

fun ToolsFragment.setOnClickListeners() {
    binding.apply {
        fragmentToolsPencilButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PENCIL_TOOL_IDENTIFIER)
        }

        fragmentToolsFillButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FILL_TOOL_IDENTIFIER)
        }

        fragmentToolsVerticalMirrorButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER)
        }

        fragmentToolsHorizontalMirrorButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER)
        }

        fragmentToolsLineButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LINE_TOOL_IDENTIFIER)
        }

        fragmentToolsRectangleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.RECTANGLE_TOOL_IDENTIFIER)
        }

        fragmentToolsOutlinedRectangleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)
        }

        fragmentToolsSquareButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SQUARE_TOOL_IDENTIFIER)
        }

        fragmentToolsOutlinedSquareButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OUTLINED_SQUARE_TOOL_IDENTIFIER)
        }

        fragmentToolsSprayButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SPRAY_TOOL_IDENTIFIER)
        }

        fragmentToolsPolygonButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.POLYGON_TOOL_IDENTIFIER)
        }

        fragmentToolsDitherButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DITHER_TOOL_IDENTIFIER)
        }

        fragmentToolsDarkenButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DARKEN_TOOL_IDENTIFIER)
        }

        fragmentToolsLightenButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LIGHTEN_TOOL_IDENTIFIER)
        }

        fragmentToolsResetCanvasButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.CLEAR_CANVAS_TOOL_IDENTIFIER)
        }

        fragmentToolsColorPickerButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.COLOR_PICKER_TOOL_IDENTIFIER)
        }

        fragmentToolsFindAndReplaceButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FIND_AND_REPLACE_TOOL_IDENTIFIER)
        }

        fragmentToolsEraseButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.ERASE_TOOL_IDENTIFIER)
        }

        fragmentToolsGridButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.GRID_TOOL_IDENTIFIER)
        }
    }
}