package com.realtomjoney.pyxlmoose.fragments.tools

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun ToolsFragment.getSelectedStateListPairData() = Pair(AppCompatResources.getColorStateList(context!!, android.R.color.holo_blue_dark), ContextCompat.getColorStateList(requireContext(), R.color.white))
fun ToolsFragment.getUnselectedStateListPairData() = Pair(AppCompatResources.getColorStateList(context!!, android.R.color.transparent), ContextCompat.getColorStateList(requireContext(), android.R.color.holo_blue_dark))

fun ToolsFragment.getToggleSelectedStateListPairData() = Pair(AppCompatResources.getColorStateList(context!!, android.R.color.holo_orange_light), ContextCompat.getColorStateList(requireContext(), R.color.white))

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
        fragmentToolsPencilButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PencilToolIdentifier)
        }

        fragmentToolsFillButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FillToolIdentifier)
        }

        fragmentToolsVerticalMirrorButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.VerticalMirrorToolIdentifier)
        }

        fragmentToolsHorizontalMirrorButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.HorizontalMirrorToolIdentifier)
        }

        fragmentToolsLineButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LineToolIdentifier)
        }

        fragmentToolsRectangleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.RectangleToolIdentifier)
        }

        fragmentToolsOutlinedRectangleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedRectangleToolIdentifier)
        }

        fragmentToolsSquareButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SquareToolIdentifier)
        }

        fragmentToolsOutlinedSquareButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedSquareToolIdentifier)
        }

        fragmentToolsCircleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.CircleToolIdentifier)
        }

        fragmentToolsOutlinedCircleButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedCircleToolIdentifier)
        }

        fragmentToolsSprayButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SprayToolIdentifier)
        }

        fragmentToolsPolygonButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PolygonToolIdentifier)
        }

        fragmentToolsDitherButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DitherToolIdentifier)
        }

        fragmentToolsDarkenButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DarkenToolIdentifier)
        }

        fragmentToolsLightenButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LightenToolIdentifier)
        }

        fragmentToolsResetCanvasButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.ClearCanvasToolIdentifier)
        }

        fragmentToolsColorPickerButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.ColorPickerToolIdentifier)
        }

        fragmentToolsFindAndReplaceButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FindAndReplaceToolIdentifier)
        }

        fragmentToolsEraseButton.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.EraseToolIdentifier)
        }

        fragmentToolsGridButton.setOnClickListener {
            if (!outerCanvasInstance.canvasFragment.myCanvasViewInstance.gridEnabled) {
                onToggleOptionTapped(it)
            } else {
                onToggleOptionTappedUnset(it)
            }
            caller.onToolTapped(StringConstants.GridToolIdentifier)
        }
    }

    binding.apply {
        fragmentToolsPencilButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PencilToolIdentifier)
        }

        fragmentToolsFillButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FillToolIdentifier)
        }

        fragmentToolsVerticalMirrorButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.VerticalMirrorToolIdentifier)
        }

        fragmentToolsHorizontalMirrorButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.HorizontalMirrorToolIdentifier)
        }

        fragmentToolsLineButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LineToolIdentifier)
        }

        fragmentToolsRectangleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.RectangleToolIdentifier)
        }

        fragmentToolsOutlinedRectangleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedRectangleToolIdentifier)
        }

        fragmentToolsSquareButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SquareToolIdentifier)
        }

        fragmentToolsOutlinedSquareButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedSquareToolIdentifier)
        }

        fragmentToolsCircleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.CircleToolIdentifier)
        }

        fragmentToolsOutlinedCircleButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.OutlinedCircleToolIdentifier)
        }

        fragmentToolsSprayButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.SprayToolIdentifier)
        }

        fragmentToolsPolygonButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.PolygonToolIdentifier)
        }

        fragmentToolsDitherButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DitherToolIdentifier)
        }

        fragmentToolsDarkenButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.DarkenToolIdentifier)
        }

        fragmentToolsLightenButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.LightenToolIdentifier)
        }

        fragmentToolsResetCanvasButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.ClearCanvasToolIdentifier)
        }

        fragmentToolsColorPickerButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.ColorPickerToolIdentifier)
        }

        fragmentToolsFindAndReplaceButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.FindAndReplaceToolIdentifier)
        }

        fragmentToolsEraseButtonH.setOnClickListener {
            onOptionTapped(it)
            caller.onToolTapped(StringConstants.EraseToolIdentifier)
        }

        fragmentToolsGridButtonH.setOnClickListener {
            if (!outerCanvasInstance.canvasFragment.myCanvasViewInstance.gridEnabled) {
                onToggleOptionTapped(it)
            } else {
                onToggleOptionTappedUnset(it)
            }
            caller.onToolTapped(StringConstants.GridToolIdentifier)
        }
    }
}