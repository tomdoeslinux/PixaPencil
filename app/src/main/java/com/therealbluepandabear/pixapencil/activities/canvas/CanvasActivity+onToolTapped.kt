package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.hideItems
import com.therealbluepandabear.pixapencil.extensions.navigateTo
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
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
        binding.activityCanvasRootLayout.showSnackbarWithAction(
            getString(R.string.spray_tool_tool_tip_in_code_str),
            SnackbarDuration.Medium,
            getString(R.string.tool_tip_dont_show_again_in_code_str)
        ) {
            with(sharedPreferenceObject.edit()) {
                putBoolean(
                    StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier,
                    false
                )
                apply()
            }
            applyShowSprayToolTipValueFromPreference()
        }
    }

    val showShadingToolTip = (
            toolName == StringConstants.Identifiers.ShadingToolIdentifier &&
                    currentTool != Tools.ShadingTool &&
                    sharedPreferenceShowShadingToolTip
            )

    if (showShadingToolTip) {
        binding.activityCanvasRootLayout.showSnackbarWithAction(
            getString(R.string.shading_tool_tool_tip_in_code_str),
            SnackbarDuration.Medium,
            getString(R.string.tool_tip_dont_show_again_in_code_str)
        ) {
            with(sharedPreferenceObject.edit()) {
                putBoolean(
                    StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier,
                    false
                )
                apply()
            }
            applyShowShadingToolTipValueFromPreference()
        }
    }

    if (toolName == StringConstants.Identifiers.SprayToolIdentifier && currentTool == Tools.SprayTool) {
        if (currentTool == Tools.SprayTool) {
            sprayToolSettingsFragmentInstance =
                SprayToolSettingsFragment.newInstance()
            currentFragmentInstance = sprayToolSettingsFragmentInstance

            navigateTo(
                supportFragmentManager,
                sprayToolSettingsFragmentInstance,
                R.id.activityCanvas_primaryFragmentHost,
                getString(R.string.fragment_spray_tool_settings_title_in_code_str),
                binding.activityCanvasPrimaryFragmentHost,
                binding.activityCanvasRootLayout
            )

            menu.hideItems()
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
        pixelGridViewInstance.currentBitmapAction = null

        polygonCoordinates.clear()
        cindx = 0
    }

    currentTool = Tools.values().firstOrNull {
        it.toolName == toolName
    } ?: Tools.defaultTool
}