/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.fragments.tools

import android.content.res.ColorStateList
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun ToolsFragment.getSelectedStateListPairData(): Pair<ColorStateList, ColorStateList?> {
    return Pair(AppCompatResources.getColorStateList(context!!, android.R.color.holo_blue_dark), ContextCompat.getColorStateList(requireContext(), R.color.white))
}

fun ToolsFragment.getUnselectedStateListPairData(): Pair<ColorStateList, ColorStateList?> {
    return Pair(AppCompatResources.getColorStateList(context!!, android.R.color.transparent), ContextCompat.getColorStateList(requireContext(), android.R.color.holo_blue_dark))
}

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
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Pencil Tool

    binding.fragmentToolsPencilButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsPencilButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsPencilButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsPencilButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Fill Tool

    binding.fragmentToolsFillButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.FILL_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsFillButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.FILL_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsFillButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.FILL_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsFillButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.FILL_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Line Tool

    binding.fragmentToolsLineButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.LINE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsLineButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.LINE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsLineButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.LINE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsLineButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.LINE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Rectangle Tool

    binding.fragmentToolsRectangleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsRectangleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsRectangleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsRectangleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Outlined Rectangle Tool

    binding.fragmentToolsOutlinedRectangleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedRectangleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsOutlinedRectangleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedRectangleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Square Tool

    binding.fragmentToolsSquareButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsSquareButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsSquareButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsSquareButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Outlined Square Tool

    binding.fragmentToolsOutlinedSquareButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedSquareButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsOutlinedSquareButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedSquareButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Ellipse Tool

    binding.fragmentToolsEllipseButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsEllipseButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsEllipseButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsEllipseButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Outlined Ellipse Tool

    binding.fragmentToolsOutlinedEllipseButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedEllipseButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsOutlinedEllipseButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedEllipseButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER)
    }

    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Circle Tool

    binding.fragmentToolsCircleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsCircleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsCircleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsCircleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Outlined Circle Tool

    binding.fragmentToolsOutlinedCircleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedCircleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsOutlinedCircleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsOutlinedCircleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Spray Tool

    binding.fragmentToolsSprayButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsSprayButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsSprayButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsSprayButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Polygon Tool

    binding.fragmentToolsPolygonButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsPolygonButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsPolygonButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsPolygonButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Dither Tool

    binding.fragmentToolsDitherButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsDitherButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsDitherButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsDitherButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Shading Tool

    binding.fragmentToolsShadingButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsShadingButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsShadingButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsShadingButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Color Picker Tool

    binding.fragmentToolsColorPickerButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsColorPickerButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsColorPickerButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsColorPickerButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Erase Tool

    binding.fragmentToolsEraseButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsEraseButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsEraseButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsEraseButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Move Tool

    binding.fragmentToolsMoveButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsMoveButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER)
    }

    // ** //

    binding.fragmentToolsMoveButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER)
    }

    binding.fragmentToolsMoveButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
}