package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.cindx
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.polygonCoordinates
import com.therealbluepandabear.pixapencil.activities.canvas.tooltips.showShadingToolTip
import com.therealbluepandabear.pixapencil.activities.canvas.tooltips.showSprayToolTip
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.fragments.spraytoolsettings.SprayToolSettingsFragment
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

    val showSprayToolTip = (
                    toolName == StringConstants.Identifiers.SprayToolIdentifier &&
                            currentTool != Tools.SprayTool &&
                            sharedPreferenceShowSprayToolTip
    )

    if (showSprayToolTip) {
        showSprayToolTip()
    }

    val showShadingToolTip = (
            toolName == StringConstants.Identifiers.ShadingToolIdentifier &&
                    currentTool != Tools.ShadingTool &&
                    sharedPreferenceShowShadingToolTip
            )

    if (showShadingToolTip) {
        showShadingToolTip()
    }

    if (toolName == StringConstants.Identifiers.SprayToolIdentifier && currentTool == Tools.SprayTool) {
        if (currentTool == Tools.SprayTool) {
            supportFragmentManager.commit {
                replace(
                    R.id.activityCanvas_primaryFragmentHost,  SprayToolSettingsFragment.newInstance())
                addToBackStack(null)
            }
        }
    }

    if (toolName == StringConstants.Identifiers.ShadingToolIdentifier && currentTool == Tools.ShadingTool) {
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

    if (toolName == StringConstants.Identifiers.PolygonToolIdentifier && currentTool == Tools.PolygonTool) {
        viewModel.currentBitmapAction = null

        polygonCoordinates.clear()
        cindx = 0
    }

    currentTool = Tools.values().firstOrNull {
        it.toolName == toolName
    } ?: Tools.defaultTool
}