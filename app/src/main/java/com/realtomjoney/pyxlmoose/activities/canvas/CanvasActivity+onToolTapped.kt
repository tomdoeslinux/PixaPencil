package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.fragments.findandreplace.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.realtomjoney.pyxlmoose.utility.Flags
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    if (currentTool == Tools.POLYGON_TOOL && toolName != StringConstants.POLYGON_TOOL_IDENTIFIER) {
        Flags.DISABLE_ACTION_MOVE = false
        polygonCoordinates.clear()
        cindx = 0
    }

    when (toolName) {
        StringConstants.PENCIL_TOOL_IDENTIFIER -> currentTool = Tools.PENCIL_TOOL

        StringConstants.FILL_TOOL_IDENTIFIER  -> currentTool = Tools.FILL_TOOL

        StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER  -> currentTool = Tools.VERTICAL_MIRROR_TOOL

        StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER -> currentTool = Tools.HORIZONTAL_MIRROR_TOOL

        StringConstants.LINE_TOOL_IDENTIFIER -> currentTool = Tools.LINE_TOOL

        StringConstants.RECTANGLE_TOOL_IDENTIFIER -> currentTool = Tools.RECTANGLE_TOOL

        StringConstants.OUTLINED_RECTANGLE_TOOL_IDENTIFIER -> currentTool = Tools.OUTLINED_RECTANGLE_TOOL

        StringConstants.SPRAY_TOOL_IDENTIFIER -> {
            if (currentTool == Tools.SPRAY_TOOL) {
                sprayToolSettingsFragmentInstance =
                    SprayToolSettingsFragment.newInstance()
                currentFragmentInstance = sprayToolSettingsFragmentInstance

                navigateTo(
                    supportFragmentManager,
                    sprayToolSettingsFragmentInstance,
                    R.id.activityCanvas_primaryFragmentHost,
                    StringConstants.FRAGMENT_SPRAY_TOOL_SETTINGS_TITLE,
                    binding.activityCanvasPrimaryFragmentHost,
                    binding.activityCanvasRootLayout
                )

                hideMenuItems()
            }

            currentTool = Tools.SPRAY_TOOL
        }

        StringConstants.POLYGON_TOOL_IDENTIFIER -> {
            if (currentTool == Tools.POLYGON_TOOL) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = null

                polygonCoordinates.clear()
                cindx = 0
            }
            currentTool = Tools.POLYGON_TOOL
        }

        StringConstants.DITHER_TOOL_IDENTIFIER -> {
            currentTool = Tools.DITHER_TOOL
        }

        StringConstants.DARKEN_TOOL_IDENTIFIER  -> {
            filterSelectedColor(Color.BLACK, 0.2f)
            currentTool = Tools.DARKEN_TOOL
        }
        StringConstants.LIGHTEN_TOOL_IDENTIFIER  -> {
            filterSelectedColor(Color.WHITE, 0.2f)
            currentTool = Tools.LIGHTEN_TOOL
        }
        StringConstants.CLEAR_CANVAS_TOOL_IDENTIFIER  -> {
            showDialog(
                StringConstants.DIALOG_CLEAR_CANVAS_TITLE,
                StringConstants.DIALOG_CLEAR_CANVAS_MESSAGE,
                StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
                { _, _ ->
                    clearCanvas()
                }, StringConstants.DIALOG_NEGATIVE_BUTTON_TEXT, { _, _ -> }, null)
        }

        StringConstants.COLOR_PICKER_TOOL_IDENTIFIER -> currentTool = Tools.COLOR_PICKER_TOOL

        StringConstants.FIND_AND_REPLACE_TOOL_IDENTIFIER  -> {
            val uniqueColors = outerCanvasInstance.canvasFragment.myCanvasViewInstance.getNumberOfUniqueColors()

            if (uniqueColors.isNotEmpty()) {
                findAndReplaceFragmentInstance =
                    FindAndReplaceFragment.newInstance(outerCanvasInstance.canvasFragment.myCanvasViewInstance.getNumberOfUniqueColors())
                currentFragmentInstance = findAndReplaceFragmentInstance
                navigateTo(
                    supportFragmentManager,
                    findAndReplaceFragmentInstance,
                    R.id.activityCanvas_primaryFragmentHost,
                    StringConstants.FRAGMENT_FIND_AND_REPLACE_TITLE,
                    binding.activityCanvasPrimaryFragmentHost,
                    binding.activityCanvasRootLayout
                )
                hideMenuItems()
            } else {
                binding.activityCanvasRootLayout.showSnackbar("You must have at least one color on your canvas to use this tool", SnackbarDuration.DEFAULT)
            }
        }
        StringConstants.ERASE_TOOL_IDENTIFIER -> currentTool = Tools.ERASE_TOOL
    }
}