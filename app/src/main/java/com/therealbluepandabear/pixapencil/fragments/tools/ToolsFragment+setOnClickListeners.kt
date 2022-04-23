package com.therealbluepandabear.pixapencil.fragments.tools

import android.content.res.ColorStateList
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.StringConstants

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
    binding.apply {
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Pencil Tool

        fragmentToolsPencilButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.PencilToolIdentifier)
        }

        fragmentToolsPencilButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.PencilToolIdentifier)
        }

        // ** //

        fragmentToolsPencilButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.PencilToolIdentifier)
            true
        }

        fragmentToolsPencilButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.PencilToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Fill Tool

        fragmentToolsFillButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.FillToolIdentifier)
        }

        fragmentToolsFillButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.FillToolIdentifier)
        }

        // ** //

        fragmentToolsFillButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.FillToolIdentifier)
            true
        }

        fragmentToolsFillButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.FillToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Line Tool

        fragmentToolsLineButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.LineToolIdentifier)
        }

        fragmentToolsLineButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.LineToolIdentifier)
        }

        // ** //

        fragmentToolsLineButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.LineToolIdentifier)
            true
        }

        fragmentToolsLineButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.LineToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Rectangle Tool

        fragmentToolsRectangleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.RectangleToolIdentifier)
        }

        fragmentToolsRectangleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.RectangleToolIdentifier)
        }

        // ** //

        fragmentToolsRectangleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.RectangleToolIdentifier)
            true
        }

        fragmentToolsRectangleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.RectangleToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Outlined Rectangle Tool

        fragmentToolsOutlinedRectangleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
        }

        fragmentToolsOutlinedRectangleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
        }

        // ** //

        fragmentToolsOutlinedRectangleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
            true
        }

        fragmentToolsOutlinedRectangleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Square Tool

        fragmentToolsSquareButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.SquareToolIdentifier)
        }

        fragmentToolsSquareButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.SquareToolIdentifier)
        }

        // ** //

        fragmentToolsSquareButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.SquareToolIdentifier)
            true
        }

        fragmentToolsSquareButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.SquareToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Outlined Square Tool

        fragmentToolsOutlinedSquareButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
        }

        fragmentToolsOutlinedSquareButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
        }

        // ** //

        fragmentToolsOutlinedSquareButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
            true
        }

        fragmentToolsOutlinedSquareButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
             caller.onToolLongTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Circle Tool

        fragmentToolsCircleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.CircleToolIdentifier)
        }

        fragmentToolsCircleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.CircleToolIdentifier)
        }

        // ** //

        fragmentToolsCircleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.CircleToolIdentifier)
            true
        }

        fragmentToolsCircleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.CircleToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Outlined Circle Tool

        fragmentToolsOutlinedCircleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
        }

        fragmentToolsOutlinedCircleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
        }

        // ** //

        fragmentToolsOutlinedCircleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
            true
        }

        fragmentToolsOutlinedCircleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Spray Tool

        fragmentToolsSprayButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.SprayToolIdentifier)
        }

        fragmentToolsSprayButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.SprayToolIdentifier)
        }

        // ** //

        fragmentToolsSprayButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.SprayToolIdentifier)
            true
        }

        fragmentToolsSprayButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.SprayToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Polygon Tool

        fragmentToolsPolygonButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.PolygonToolIdentifier)
        }

        fragmentToolsPolygonButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.PolygonToolIdentifier)
        }

        // ** //

        fragmentToolsPolygonButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.PolygonToolIdentifier)
            true
        }

        fragmentToolsPolygonButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.PolygonToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Dither Tool

        fragmentToolsDitherButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.DitherToolIdentifier)
        }

        fragmentToolsDitherButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.DitherToolIdentifier)
        }

        // ** //

        fragmentToolsDitherButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.DitherToolIdentifier)
            true
        }

        fragmentToolsDitherButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.DitherToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Shading Tool

        fragmentToolsShadingButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.ShadingToolIdentifier)
        }

        fragmentToolsShadingButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.ShadingToolIdentifier)
        }

        // ** //

        fragmentToolsShadingButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.ShadingToolIdentifier)
            true
        }

        fragmentToolsShadingButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.ShadingToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Color Picker Tool

        fragmentToolsColorPickerButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
        }

        fragmentToolsColorPickerButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
        }

        // ** //

        fragmentToolsColorPickerButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
            true
        }

        fragmentToolsColorPickerButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Erase Tool

        fragmentToolsEraseButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.EraseToolIdentifier)
        }

        fragmentToolsEraseButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.Identifiers.EraseToolIdentifier)
        }

        // ** //

        fragmentToolsEraseButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.EraseToolIdentifier)
            true
        }

        fragmentToolsEraseButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.Identifiers.EraseToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
    }
}