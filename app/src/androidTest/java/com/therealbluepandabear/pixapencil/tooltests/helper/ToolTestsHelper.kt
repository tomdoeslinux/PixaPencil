package com.therealbluepandabear.pixapencil.tooltests.helper

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.initPrimaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.BitmapAction

object ToolTestsHelper {
    fun prepare(activity: CanvasActivity) {
        activity.viewModel.currentTool = Tool.CircleTool
        activity.viewModel.bitmapActionData = mutableListOf()
        activity.viewModel.currentBitmapAction = BitmapAction(mutableListOf())
        activity.initPrimaryAlgorithmInfoParameter()
    }
}