package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.Coordinates

class RectangleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val borderColor: Int? = null) {
    private fun drawBorder(from: Coordinates, to: Coordinates) {
        val modifiedShapeAlgorithmInfo = algorithmInfo
        modifiedShapeAlgorithmInfo.color = borderColor!!

        val lineAlgorithmInstance = LineAlgorithm(modifiedShapeAlgorithmInfo)

        lineAlgorithmInstance.apply {
            compute(Coordinates(from.x, from.y), Coordinates(to.x, from.y))
            compute(Coordinates(to.x, to.y), Coordinates(to.x, from.y))
            compute(Coordinates(from.x, to.y), Coordinates(from.x, from.y))
            compute(Coordinates(to.x, to.y), Coordinates(from.x, to.y))
        }
    }

    fun compute(p1: Coordinates, p2: Coordinates) {
        var x = p1.x
        val y = p1.y

        if (p1.x >= p2.x && p1.y <= p2.y) {
            while (x >= p2.x) {
                for (i in y..p2.y) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, i, algorithmInfo.color)
                }

                outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, y, algorithmInfo.color)

                x--
            }
        } else if (p1.x <= p2.x && p1.y <= p2.y) {
            while (x <= p2.x) {
                for (i in y..p2.y) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, i, algorithmInfo.color)
                }

                outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, y, algorithmInfo.color)

                x++
            }
        } else if (p1.x <= p2.x && p1.y >= p2.y) {
            while (x <= p2.x) {
                for (i in p2.y..y) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, i, algorithmInfo.color)
                }

                outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, y, algorithmInfo.color)

                x++
            }
        } else if (p1.x >= p2.x && p1.y >= p2.y) {
            while (x >= p2.x) {
                for (i in p2.y..y) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, i, algorithmInfo.color)
                }

                outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(x, y, algorithmInfo.color)

                x--
            }
        }
        if (borderColor != null) {
            drawBorder(p1, p2)
        }
    }
}