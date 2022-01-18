package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.realtomjoney.pyxlmoose.activities.canvas.canvasInstance
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition
import kotlin.math.abs

class LineAlgorithm(private val bitmap: Bitmap, private val currentBitmapAction: BitmapAction, private val color: Int = Color.BLACK) {
    private fun drawLineY(from: XYPosition, to: XYPosition) {
        var x = from.x
        var y = from.y

        val differenceX = to.x - x
        var differenceY = to.y - y

        var yi = 1
        val xi = 1

        if (differenceY < 0) {
            differenceY = -differenceY
            yi = -1
        }

        var p = 2 * differenceY - differenceX

        while (x <= to.x) {
            currentBitmapAction.actionData.add(BitmapActionData(
                XYPosition(x, y),
                bitmap.getPixel(x, y),
            ))
            canvasInstance.myCanvasViewInstance.overrideSetPixel(x, y, color)
            x++

            if (p < 0) {
                p += 2 * differenceY
                if (differenceY > differenceX) {
                    x += xi
                }
            } else {
                p = p + 2 * differenceY - 2 * differenceX
                y += yi
            }
        }
    }

    private fun drawLineX(from: XYPosition, to: XYPosition) {
        var x = from.x
        var y = from.y

        var differenceX = to.x - x
        val differenceY = to.y - y

        var xi = 1

        if (differenceX <= 0) {
            differenceX = -differenceX
            xi = -1
        }

        var p = 2 * differenceX - differenceY

        while (y <= to.y) {
            currentBitmapAction.actionData.add(BitmapActionData(
                XYPosition(x, y),
                bitmap.getPixel(x, y),
            ))
            canvasInstance.myCanvasViewInstance.overrideSetPixel(x, y, color)
            y++

            if (p < 0) {
                p += 2 * differenceX
            } else {
                p = p + 2 * differenceX - 2 * differenceY
                x += xi
            }
        }
    }

    fun compute(from: XYPosition, to: XYPosition) {
        val x = from.x
        val y = from.y

        val differenceX = to.x - x
        val differenceY = to.y - y

        if (differenceY <= differenceX) {
            if (abs(differenceY) > differenceX) {
                drawLineX(to, from)
            } else {
                drawLineY(from, to)
            }
        } else if (differenceX < differenceY) {
            if (abs(differenceX) > differenceY) {
                drawLineY(to, from)
            } else {
                drawLineX(from, to)
            }
        }
    }
}