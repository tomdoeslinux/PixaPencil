package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    when (toolName) {
        "PENCIL" -> {
            currentTool = Tools.PENCIL_TOOL
        }
        "VERTICAL_MIRROR" -> {
            currentTool = Tools.VERTICAL_MIRROR_TOOL
        }
        "HORIZONTAL_MIRROR" -> {
            currentTool = Tools.HORIZONTAL_MIRROR_TOOL
        }
        "DARKEN" -> {
            darkenSelectedColor()
            currentTool = Tools.DARKEN_TOOL
        }
        "LIGHTEN" -> {
            lightenSelectedColor()
            currentTool = Tools.LIGHTEN_TOOL
        }
        "RESET_CANVAS" -> {
            showDialog(
                "Clear canvas",
                "Are you sure you want to clear the canvas? This cannot be undone.",
                StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
                { _, _ ->
                    clearCanvas()
                }, StringConstants.DIALOG_NEGATIVE_BUTTON_TEXT, { _, _ -> }, null)
        }
        "CHANGE_BACKGROUND" -> {
            currentTool = Tools.CHANGE_BACKGROUND_TOOL
        }
        "COLOR_PICKER" -> {
            currentTool = Tools.COLOR_PICKER_TOOL
        }
        "FIND_AND_REPLACE" -> {
            findAndReplaceFragmentInstance = FindAndReplaceFragment.newInstance(extendedGetCanvasColors())
            currentFragmentInstance = findAndReplaceFragmentInstance
            navigateTo(supportFragmentManager, findAndReplaceFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FRAGMENT_FIND_AND_REPLACE_TITLE, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
        }
        "ERASE" -> {
            currentTool = Tools.ERASE_TOOL
        }
    }
}