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
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
    // *************** // Pencil Tool

    binding.fragmentToolsPencilButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.PencilToolIdentifier)
    }

    binding.fragmentToolsPencilButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.PencilToolIdentifier)
    }

    // ** //

    binding.fragmentToolsPencilButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PencilToolIdentifier)
        true
    }

    binding.fragmentToolsPencilButtonH.setOnLongClickListener {
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

    binding.fragmentToolsFillButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.FillToolIdentifier)
    }

    binding.fragmentToolsFillButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.FillToolIdentifier)
    }

    // ** //

    binding.fragmentToolsFillButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.FillToolIdentifier)
        true
    }

    binding.fragmentToolsFillButtonH.setOnLongClickListener {
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

    binding.fragmentToolsLineButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.LineToolIdentifier)
    }

    binding.fragmentToolsLineButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.LineToolIdentifier)
    }

    // ** //

    binding.fragmentToolsLineButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.LineToolIdentifier)
        true
    }

    binding.fragmentToolsLineButtonH.setOnLongClickListener {
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

    binding.fragmentToolsRectangleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.RectangleToolIdentifier)
    }

    binding.fragmentToolsRectangleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.RectangleToolIdentifier)
    }

    // ** //

    binding.fragmentToolsRectangleButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.RectangleToolIdentifier)
        true
    }

    binding.fragmentToolsRectangleButtonH.setOnLongClickListener {
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

    binding.fragmentToolsOutlinedRectangleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
    }

    binding.fragmentToolsOutlinedRectangleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
    }

    // ** //

    binding.fragmentToolsOutlinedRectangleButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
        true
    }

    binding.fragmentToolsOutlinedRectangleButtonH.setOnLongClickListener {
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

    binding.fragmentToolsSquareButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SquareToolIdentifier)
    }

    binding.fragmentToolsSquareButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SquareToolIdentifier)
    }

    // ** //

    binding.fragmentToolsSquareButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SquareToolIdentifier)
        true
    }

    binding.fragmentToolsSquareButtonH.setOnLongClickListener {
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

    binding.fragmentToolsOutlinedSquareButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
    }

    binding.fragmentToolsOutlinedSquareButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
    }

    // ** //

    binding.fragmentToolsOutlinedSquareButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
        true
    }

    binding.fragmentToolsOutlinedSquareButtonH.setOnLongClickListener {
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

    binding.fragmentToolsCircleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.CircleToolIdentifier)
    }

    binding.fragmentToolsCircleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.CircleToolIdentifier)
    }

    // ** //

    binding.fragmentToolsCircleButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.CircleToolIdentifier)
        true
    }

    binding.fragmentToolsCircleButtonH.setOnLongClickListener {
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

    binding.fragmentToolsOutlinedCircleButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
    }

    binding.fragmentToolsOutlinedCircleButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
    }

    // ** //

    binding.fragmentToolsOutlinedCircleButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
        true
    }

    binding.fragmentToolsOutlinedCircleButtonH.setOnLongClickListener {
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

    binding.fragmentToolsSprayButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SprayToolIdentifier)
    }

    binding.fragmentToolsSprayButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.SprayToolIdentifier)
    }

    // ** //

    binding.fragmentToolsSprayButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SprayToolIdentifier)
        true
    }

    binding.fragmentToolsSprayButtonH.setOnLongClickListener {
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

    binding.fragmentToolsPolygonButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.PolygonToolIdentifier)
    }

    binding.fragmentToolsPolygonButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.PolygonToolIdentifier)
    }

    // ** //

    binding.fragmentToolsPolygonButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PolygonToolIdentifier)
        true
    }

    binding.fragmentToolsPolygonButtonH.setOnLongClickListener {
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

    binding.fragmentToolsDitherButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.DitherToolIdentifier)
    }

    binding.fragmentToolsDitherButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.DitherToolIdentifier)
    }

    // ** //

    binding.fragmentToolsDitherButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.DitherToolIdentifier)
        true
    }

    binding.fragmentToolsDitherButtonH.setOnLongClickListener {
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

    binding.fragmentToolsShadingButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ShadingToolIdentifier)
    }

    binding.fragmentToolsShadingButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ShadingToolIdentifier)
    }

    // ** //

    binding.fragmentToolsShadingButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ShadingToolIdentifier)
        true
    }

    binding.fragmentToolsShadingButtonH.setOnLongClickListener {
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

    binding.fragmentToolsColorPickerButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
    }

    binding.fragmentToolsColorPickerButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
    }

    // ** //

    binding.fragmentToolsColorPickerButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
        true
    }

    binding.fragmentToolsColorPickerButtonH.setOnLongClickListener {
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

    binding.fragmentToolsEraseButton.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.EraseToolIdentifier)
    }

    binding.fragmentToolsEraseButtonH.setOnClickListener {
        onOptionTapped(it)
        caller.onToolTapped(StringConstants.Identifiers.EraseToolIdentifier)
    }

    // ** //

    binding.fragmentToolsEraseButton.setOnLongClickListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.EraseToolIdentifier)
        true
    }

    binding.fragmentToolsEraseButtonH.setOnLongClickListener {
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