package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.fragments.findandreplace.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    canvasFragmentInstance.myCanvasViewInstance.removeGrid()
    when (toolName) {
        StringConstants.PENCIL_TOOL_IDENTIFIER -> currentTool = Tools.PENCIL_TOOL

        StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER  -> currentTool = Tools.VERTICAL_MIRROR_TOOL

        StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER -> currentTool = Tools.HORIZONTAL_MIRROR_TOOL

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
        StringConstants.CHANGE_BACKGROUND_TOOL_IDENTIFIER  -> currentTool = Tools.CHANGE_BACKGROUND_TOOL

        StringConstants.COLOR_PICKER_TOOL_IDENTIFIER -> currentTool = Tools.COLOR_PICKER_TOOL

        StringConstants.FIND_AND_REPLACE_TOOL_IDENTIFIER  -> {
            findAndReplaceFragmentInstance = FindAndReplaceFragment.newInstance(extendedGetCanvasColors())
            currentFragmentInstance = findAndReplaceFragmentInstance
            navigateTo(supportFragmentManager, findAndReplaceFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FRAGMENT_FIND_AND_REPLACE_TITLE, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
        }
        StringConstants.ERASE_TOOL_IDENTIFIER -> currentTool = Tools.ERASE_TOOL

        StringConstants.GRID_TOOL_IDENTIFIER -> {
            if (!gridEnabled) canvasFragmentInstance.myCanvasViewInstance.drawGrid(canvasFragmentInstance.myCanvasViewInstance.extraCanvas)
            else canvasFragmentInstance.myCanvasViewInstance.removeGrid()

            gridEnabled = !gridEnabled
        }
    }
}