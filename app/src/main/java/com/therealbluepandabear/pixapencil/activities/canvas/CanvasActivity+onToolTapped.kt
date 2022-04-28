package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    if (currentTool == Tools.ShadingTool && toolName != StringConstants.Identifiers.ShadingToolIdentifier) {
        pixelGridViewInstance.shadingMode = false
    }

    if (currentTool == Tools.PolygonTool && toolName != StringConstants.Identifiers.PolygonToolIdentifier) {
        Flags.DisableActionMove = false
        polygonCoordinates.clear()
        cindx = 0
    }

    when (toolName) {
        StringConstants.Identifiers.PencilToolIdentifier -> {
            currentTool = Tools.PencilTool
        }

        StringConstants.Identifiers.EraseToolIdentifier -> {
            currentTool = Tools.EraseTool
        }

        StringConstants.Identifiers.ColorPickerToolIdentifier -> {
            currentTool = Tools.ColorPickerTool
        }

        StringConstants.Identifiers.FillToolIdentifier  -> {
            currentTool = Tools.FillTool
        }

        StringConstants.Identifiers.LineToolIdentifier -> {
            currentTool = Tools.LineTool
        }

        StringConstants.Identifiers.RectangleToolIdentifier -> {
            currentTool = Tools.RectangleTool
        }

        StringConstants.Identifiers.OutlinedRectangleToolIdentifier -> {
            currentTool = Tools.OutlinedRectangleTool
        }

        StringConstants.Identifiers.SquareToolIdentifier -> {
            currentTool = Tools.SquareTool
        }

        StringConstants.Identifiers.OutlinedSquareToolIdentifier -> {
            currentTool = Tools.OutlinedSquareTool
        }

        StringConstants.Identifiers.CircleToolIdentifier -> {
            currentTool = Tools.CircleTool
        }

        StringConstants.Identifiers.OutlinedCircleToolIdentifier -> {
            currentTool = Tools.OutlinedCircleTool
        }

        StringConstants.Identifiers.SprayToolIdentifier -> {
            sprayToolOnToolTapped()
        }

        StringConstants.Identifiers.PolygonToolIdentifier -> {
            if (currentTool == Tools.PolygonTool) {
                pixelGridViewInstance.currentBitmapAction = null

                polygonCoordinates.clear()
                cindx = 0
            }
            currentTool = Tools.PolygonTool
        }

        StringConstants.Identifiers.DitherToolIdentifier -> {
            currentTool = Tools.DitherTool
        }

        StringConstants.Identifiers.ShadingToolIdentifier  -> {
            if (currentTool == Tools.ShadingTool) {
                val snackbarText: String = if (shadingToolMode == "Lighten") {
                   "Darken mode".also {
                       shadingToolMode = "Darken"
                   }
                } else {
                    "Lighten mode".also {
                        shadingToolMode = "Lighten"
                    }
                }

                binding.activityCanvasRootLayout.showSnackbar(snackbarText, SnackbarDuration.Short)
            }

            if (sharedPreferenceShowShadingToolTip && currentTool != Tools.ShadingTool) {
                binding.activityCanvasRootLayout.showSnackbarWithAction(getString(R.string.shading_tool_tool_tip_in_code_str), SnackbarDuration.Medium, getString(R.string.tool_tip_dont_show_again_in_code_str)) {
                    with (sharedPreferenceObject.edit()) {
                        putBoolean(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier, false)
                        apply()
                    }
                    applyShowShadingToolTipValueFromPreference()
                }
            }

            currentTool = Tools.ShadingTool
        }
    }
}