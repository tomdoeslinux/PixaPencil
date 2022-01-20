package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.database.BrushesDatabase
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData

fun CanvasActivity.extendedOnActionUp() {
    if (currentTool == Tools.LINE_TOOL) {
        lineOrigin = null
        lineMode_hasLetGo = true
        rectangleMode_hasLetGo = true
    } else if (currentTool == Tools.RECTANGLE_TOOL || currentTool == Tools.OUTLINED_RECTANGLE_TOOL) {
        rectangleOrigin = null
        rectangleMode_hasLetGo = true
    } else {
        canvasInstance.myCanvasViewInstance.bitmapActionData.add(canvasInstance.myCanvasViewInstance.currentBitmapAction!!)

        if (canvasInstance.myCanvasViewInstance.pixelPerfectMode
            && (currentTool == Tools.PENCIL_TOOL)
            && (canvasInstance.myCanvasViewInstance.currentBrush == null || canvasInstance.myCanvasViewInstance.currentBrush == BrushesDatabase.toList().first())) {

            // Thanks to https://rickyhan.com/jekyll/update/2018/11/22/pixel-art-algorithm-pixel-perfect.html

            var distinct =
                canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.distinctBy { it.xyPosition }
            val data = mutableListOf<BitmapActionData>()

            var index = 0

            while (index < distinct.size) {
                if (index > 0 && index + 1 < distinct.size
                    && (distinct[index - 1].xyPosition.x == distinct[index].xyPosition.x || distinct[index - 1].xyPosition.y == distinct[index].xyPosition.y)
                    && (distinct[index + 1].xyPosition.x == distinct[index].xyPosition.x || distinct[index + 1].xyPosition.y == distinct[index].xyPosition.y)
                    && distinct[index - 1].xyPosition.x != distinct[index + 1].xyPosition.x
                    && distinct[index - 1].xyPosition.y != distinct[index + 1].xyPosition.y
                ) {
                    index += 1
                }

                data.add(distinct[index])

                index += 1
            }

            extendedUndo()

            for (value in data) {
                distinct = distinct.filter { it == value }
            }

            for (value in data) {
                canvasInstance.myCanvasViewInstance.pixelGridViewBitmap.setPixel(
                    value.xyPosition.x,
                    value.xyPosition.y,
                    getSelectedColor()
                )
            }

            canvasInstance.myCanvasViewInstance.bitmapActionData.add(BitmapAction(data))
        }

        canvasInstance.myCanvasViewInstance.currentBitmapAction = null

        canvasInstance.myCanvasViewInstance.prevX = null
        canvasInstance.myCanvasViewInstance.prevY = null
    }
}