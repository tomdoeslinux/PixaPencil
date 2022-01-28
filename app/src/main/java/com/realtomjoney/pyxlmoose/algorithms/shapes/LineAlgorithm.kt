package com.realtomjoney.pyxlmoose.algorithms.shapes

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates
import kotlin.math.abs

class LineAlgorithm(private val algorithmInfo: AlgorithmInfoParameter)
    : ShapeAlgorithm {
    private fun drawLineY(from: Coordinates, to: Coordinates) {
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
            algorithmInfo.currentBitmapAction.actionData.add(BitmapActionData(
                Coordinates(x, y),
                algorithmInfo.bitmap.getPixel(x, y),
            ))
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, y, algorithmInfo.color)
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

    private fun drawLineX(from: Coordinates, to: Coordinates) {
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
            algorithmInfo.currentBitmapAction.actionData.add(BitmapActionData(
                Coordinates(x, y),
                algorithmInfo.bitmap.getPixel(x, y),
            ))
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, y, algorithmInfo.color)
            y++

            if (p < 0) {
                p += 2 * differenceX
            } else {
                p = p + 2 * differenceX - 2 * differenceY
                x += xi
            }
        }
    }

    override fun compute(p1: Coordinates, p2: Coordinates) {
        val x = p1.x
        val y = p1.y

        val differenceX = p2.x - x
        val differenceY = p2.y - y

        if (differenceY <= differenceX) {
            if (abs(differenceY) > differenceX) {
                drawLineX(p2, p1)
            } else {
                drawLineY(p1, p2)
            }
        } else if (differenceX < differenceY) {
            if (abs(differenceX) > differenceY) {
                drawLineY(p2, p1)
            } else {
                drawLineX(p1, p2)
            }
        }
    }
}