package com.realtomjoney.pyxlmoose.activities.canvas

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

        if (canvasInstance.myCanvasViewInstance.pixelPerfectMode && currentTool == Tools.PENCIL_TOOL || currentTool == Tools.VERTICAL_MIRROR_TOOL || currentTool == Tools.HORIZONTAL_MIRROR_TOOL || currentTool == Tools.ERASE_TOOL) {

            // Thanks to https://rickyhan.com/jekyll/update/2018/11/22/pixel-art-algorithm-pixel-perfect.html

            var distinct =
                canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.distinctBy { it.xyPosition }
            val data = mutableListOf<BitmapActionData>()

            var c = 0

            while (c < distinct.size) {
                if (c > 0 && c + 1 < distinct.size
                    && (distinct[c - 1].xyPosition.x == distinct[c].xyPosition.x || distinct[c - 1].xyPosition.y == distinct[c].xyPosition.y)
                    && (distinct[c + 1].xyPosition.x == distinct[c].xyPosition.x || distinct[c + 1].xyPosition.y == distinct[c].xyPosition.y)
                    && distinct[c - 1].xyPosition.x != distinct[c + 1].xyPosition.x
                    && distinct[c - 1].xyPosition.y != distinct[c + 1].xyPosition.y
                ) {
                    c += 1
                }

                data.add(distinct[c])

                c += 1
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