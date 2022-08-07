package com.therealbluepandabear.pixapencil.tooltests.helper

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.initPrimaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.BitmapAction
import java.util.*

object ToolTestsHelper {
    fun prepare(activity: CanvasActivity) {
        activity.viewModel.currentTool = Tool.CircleTool
        activity.viewModel.undoStack = Stack()
        activity.viewModel.currentBitmapAction = BitmapAction(mutableListOf())
        activity.initPrimaryAlgorithmInfoParameter()
    }
}