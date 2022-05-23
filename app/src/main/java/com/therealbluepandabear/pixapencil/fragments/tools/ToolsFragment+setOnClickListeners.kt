package com.therealbluepandabear.pixapencil.fragments.tools

import android.content.res.ColorStateList
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
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

    binding.fragmentToolsPencilButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PencilToolIdentifier)
    }

    binding.fragmentToolsPencilButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PencilToolIdentifier)
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

    binding.fragmentToolsFillButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.FillToolIdentifier)
    }

    binding.fragmentToolsFillButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.FillToolIdentifier)
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

    binding.fragmentToolsLineButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.LineToolIdentifier)
    }

    binding.fragmentToolsLineButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.LineToolIdentifier)
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

    binding.fragmentToolsRectangleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.RectangleToolIdentifier)
    }

    binding.fragmentToolsRectangleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.RectangleToolIdentifier)
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

    binding.fragmentToolsOutlinedRectangleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
    }

    binding.fragmentToolsOutlinedRectangleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedRectangleToolIdentifier)
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

    binding.fragmentToolsSquareButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SquareToolIdentifier)
    }

    binding.fragmentToolsSquareButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SquareToolIdentifier)
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

    binding.fragmentToolsOutlinedSquareButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
    }

    binding.fragmentToolsOutlinedSquareButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedSquareToolIdentifier)
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

    binding.fragmentToolsCircleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.CircleToolIdentifier)
    }

    binding.fragmentToolsCircleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.CircleToolIdentifier)
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

    binding.fragmentToolsOutlinedCircleButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
    }

    binding.fragmentToolsOutlinedCircleButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.OutlinedCircleToolIdentifier)
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

    binding.fragmentToolsSprayButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SprayToolIdentifier)
    }

    binding.fragmentToolsSprayButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.SprayToolIdentifier)
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

    binding.fragmentToolsPolygonButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PolygonToolIdentifier)
    }

    binding.fragmentToolsPolygonButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.PolygonToolIdentifier)
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

    binding.fragmentToolsDitherButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.DitherToolIdentifier)
    }

    binding.fragmentToolsDitherButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.DitherToolIdentifier)
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

    binding.fragmentToolsShadingButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ShadingToolIdentifier)
    }

    binding.fragmentToolsShadingButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ShadingToolIdentifier)
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

    binding.fragmentToolsColorPickerButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
    }

    binding.fragmentToolsColorPickerButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.ColorPickerToolIdentifier)
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

    binding.fragmentToolsEraseButton.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.EraseToolIdentifier)
    }

    binding.fragmentToolsEraseButtonH.setOnLongPressListener {
        HapticFeedbackWrapper.performHapticFeedback(it)
        caller.onToolLongTapped(StringConstants.Identifiers.EraseToolIdentifier)
    }
    // 1 //
    // 2 //
    // 3 //
    // 4 //
    // 5 //
}