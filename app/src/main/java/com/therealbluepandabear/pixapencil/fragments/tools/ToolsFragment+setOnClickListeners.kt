package com.therealbluepandabear.pixapencil.fragments.tools

import android.content.res.ColorStateList
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun ToolsFragment.getSelectedStateListPairData(): Pair<ColorStateList, ColorStateList?> {
    return Pair(AppCompatResources.getColorStateList(context!!, android.R.color.holo_blue_dark), ContextCompat.getColorStateList(requireContext(), R.color.white))
}

fun ToolsFragment.getUnselectedStateListPairData(): Pair<ColorStateList, ColorStateList?> {
    return Pair(AppCompatResources.getColorStateList(context!!, android.R.color.transparent), ContextCompat.getColorStateList(requireContext(), android.R.color.holo_blue_dark))
}

fun ToolsFragment.getToggleSelectedStateListPairData(): Pair<ColorStateList, ColorStateList?> {
    return Pair(AppCompatResources.getColorStateList(context!!, android.R.color.holo_orange_light), ContextCompat.getColorStateList(requireContext(), R.color.white))
}

fun ToolsFragment.toggleSetColorFor(it: View) {
    it.backgroundTintList = getToggleSelectedStateListPairData().first
    (it as FloatingActionButton).supportImageTintList = getSelectedStateListPairData().second
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

fun ToolsFragment.onToggleOptionTapped(it: View) {
    toggleSetColorFor(it)
}

fun ToolsFragment.onToggleOptionTappedUnset(it: View) {
    unsetColorFor(it)
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
            caller.onToolTapped(StringConstants.PencilToolIdentifier)
        }

        fragmentToolsPencilButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PencilToolIdentifier)
        }

        // ** //

        fragmentToolsPencilButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.PencilToolIdentifier)
            true
        }

        fragmentToolsPencilButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.PencilToolIdentifier)
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
            caller.onToolTapped(StringConstants.FillToolIdentifier)
        }

        fragmentToolsFillButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FillToolIdentifier)
        }

        // ** //

        fragmentToolsFillButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.FillToolIdentifier)
            true
        }

        fragmentToolsFillButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.FillToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // VMirror Tool

        fragmentToolsVerticalMirrorButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.VerticalMirrorToolIdentifier)
        }

        fragmentToolsVerticalMirrorButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.VerticalMirrorToolIdentifier)
        }

        // ** //

        fragmentToolsVerticalMirrorButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.VerticalMirrorToolIdentifier)
            true
        }

        fragmentToolsVerticalMirrorButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.VerticalMirrorToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // HMirror Tool

        fragmentToolsHorizontalMirrorButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.HorizontalMirrorToolIdentifier)
        }

        fragmentToolsHorizontalMirrorButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.HorizontalMirrorToolIdentifier)
        }

        // ** //

        fragmentToolsHorizontalMirrorButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.HorizontalMirrorToolIdentifier)
            true
        }

        fragmentToolsHorizontalMirrorButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.HorizontalMirrorToolIdentifier)
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
            caller.onToolTapped(StringConstants.LineToolIdentifier)
        }

        fragmentToolsLineButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LineToolIdentifier)
        }

        // ** //

        fragmentToolsLineButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.LineToolIdentifier)
            true
        }

        fragmentToolsLineButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.LineToolIdentifier)
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
            caller.onToolTapped(StringConstants.RectangleToolIdentifier)
        }

        fragmentToolsRectangleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.RectangleToolIdentifier)
        }

        // ** //

        fragmentToolsRectangleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.RectangleToolIdentifier)
            true
        }

        fragmentToolsRectangleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.RectangleToolIdentifier)
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
            caller.onToolTapped(StringConstants.OutlinedRectangleToolIdentifier)
        }

        fragmentToolsOutlinedRectangleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedRectangleToolIdentifier)
        }

        // ** //

        fragmentToolsOutlinedRectangleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.OutlinedRectangleToolIdentifier)
            true
        }

        fragmentToolsOutlinedRectangleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.OutlinedRectangleToolIdentifier)
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
            caller.onToolTapped(StringConstants.SquareToolIdentifier)
        }

        fragmentToolsSquareButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SquareToolIdentifier)
        }

        // ** //

        fragmentToolsSquareButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.SquareToolIdentifier)
            true
        }

        fragmentToolsSquareButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.SquareToolIdentifier)
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
            caller.onToolTapped(StringConstants.OutlinedSquareToolIdentifier)
        }

        fragmentToolsOutlinedSquareButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedSquareToolIdentifier)
        }

        // ** //

        fragmentToolsOutlinedSquareButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.OutlinedSquareToolIdentifier)
            true
        }

        fragmentToolsOutlinedSquareButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
             caller.onToolLongTapped(StringConstants.OutlinedSquareToolIdentifier)
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
            caller.onToolTapped(StringConstants.CircleToolIdentifier)
        }

        fragmentToolsCircleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.CircleToolIdentifier)
        }

        // ** //

        fragmentToolsCircleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.CircleToolIdentifier)
            true
        }

        fragmentToolsCircleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.CircleToolIdentifier)
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
            caller.onToolTapped(StringConstants.OutlinedCircleToolIdentifier)
        }

        fragmentToolsOutlinedCircleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedCircleToolIdentifier)
        }

        // ** //

        fragmentToolsOutlinedCircleButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.OutlinedCircleToolIdentifier)
            true
        }

        fragmentToolsOutlinedCircleButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.OutlinedCircleToolIdentifier)
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
            caller.onToolTapped(StringConstants.SprayToolIdentifier)
        }

        fragmentToolsSprayButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SprayToolIdentifier)
        }

        // ** //

        fragmentToolsSprayButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.SprayToolIdentifier)
            true
        }

        fragmentToolsSprayButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.SprayToolIdentifier)
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
            caller.onToolTapped(StringConstants.PolygonToolIdentifier)
        }

        fragmentToolsPolygonButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PolygonToolIdentifier)
        }

        // ** //

        fragmentToolsPolygonButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.PolygonToolIdentifier)
            true
        }

        fragmentToolsPolygonButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.PolygonToolIdentifier)
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
            caller.onToolTapped(StringConstants.DitherToolIdentifier)
        }

        fragmentToolsDitherButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DitherToolIdentifier)
        }

        // ** //

        fragmentToolsDitherButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.DitherToolIdentifier)
            true
        }

        fragmentToolsDitherButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.DitherToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Darken Tool

        fragmentToolsDarkenButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DarkenToolIdentifier)
        }

        fragmentToolsDarkenButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DarkenToolIdentifier)
        }

        // ** //

        fragmentToolsDarkenButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.DarkenToolIdentifier)
            true
        }

        fragmentToolsDarkenButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.DarkenToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Lighten Tool

        fragmentToolsLightenButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LightenToolIdentifier)
        }

        fragmentToolsLightenButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LightenToolIdentifier)
        }

        // ** //

        fragmentToolsLightenButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.LightenToolIdentifier)
            true
        }

        fragmentToolsLightenButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.LightenToolIdentifier)
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
            caller.onToolTapped(StringConstants.ColorPickerToolIdentifier)
        }

        fragmentToolsColorPickerButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.ColorPickerToolIdentifier)
        }

        // ** //

        fragmentToolsColorPickerButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.ColorPickerToolIdentifier)
            true
        }

        fragmentToolsColorPickerButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.ColorPickerToolIdentifier)
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
            caller.onToolTapped(StringConstants.EraseToolIdentifier)
        }

        fragmentToolsEraseButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.EraseToolIdentifier)
        }

        // ** //

        fragmentToolsEraseButton.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.EraseToolIdentifier)
            true
        }

        fragmentToolsEraseButtonH.setOnLongClickListener {
            HapticFeedbackWrapper.performHapticFeedback(it)
            caller.onToolLongTapped(StringConstants.EraseToolIdentifier)
            true
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
        // *************** // Grid Tool

        fragmentToolsGridButton.setOnClickListener {
            if (!pixelGridViewInstance.gridEnabled) {
                onToggleOptionTapped(it)
            } else {
                onToggleOptionTappedUnset(it)
            }
            caller.onToolTapped(StringConstants.GridToolIdentifier)
        }

        fragmentToolsGridButtonH.setOnClickListener {
            if (!pixelGridViewInstance.gridEnabled) {
                onToggleOptionTapped(it)
            } else {
                onToggleOptionTappedUnset(it)
            }
            caller.onToolTapped(StringConstants.GridToolIdentifier)
        }
        // 1 //
        // 2 //
        // 3 //
        // 4 //
        // 5 //
    }
}