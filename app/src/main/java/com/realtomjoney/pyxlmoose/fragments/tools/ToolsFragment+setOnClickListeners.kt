package com.realtomjoney.pyxlmoose.fragments.tools

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun ToolsFragment.setColorFor(it: View) {
    it.backgroundTintList = AppCompatResources.getColorStateList(context!!, android.R.color.holo_blue_dark)
    (it as FloatingActionButton).supportImageTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
}

fun ToolsFragment.unsetColorFor(it: View) {
    it.backgroundTintList = AppCompatResources.getColorStateList(context!!, android.R.color.transparent)
    (it as FloatingActionButton).supportImageTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.holo_blue_dark)
}

var currentlySelectedFAB: FloatingActionButton? = null

fun ToolsFragment.setOnClickListeners() {
    binding.apply {
        fragmentToolsPencilButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.PENCIL_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsFillButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.FILL_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsVerticalMirrorButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsHorizontalMirrorButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsLineButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.LINE_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsRectangleButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.RECTANGLE_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsOutlinedRectangleButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsDarkenButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.DARKEN_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsLightenButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.LIGHTEN_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsResetCanvasButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.CLEAR_CANVAS_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsColorPickerButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.COLOR_PICKER_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsFindAndReplaceButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.FIND_AND_REPLACE_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsEraseButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.ERASE_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }

        fragmentToolsGridButton.setOnClickListener {
            currentlySelectedFAB?.let { it1 -> unsetColorFor(it1) }
            setColorFor(it)
            caller.onToolTapped(StringConstants.GRID_TOOL_IDENTIFIER)

            currentlySelectedFAB = it as FloatingActionButton
        }
    }
}