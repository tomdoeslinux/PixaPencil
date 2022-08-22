package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

class RectangleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val borderColor: Int? = null) {
    private fun drawBorder(from: Coordinates, to: Coordinates) {
        val modifiedShapeAlgorithmInfo = algorithmInfo
        modifiedShapeAlgorithmInfo.color = borderColor!!

        val lineAlgorithmInstance = LineAlgorithm(modifiedShapeAlgorithmInfo, true)

        lineAlgorithmInstance.compute(Coordinates(from.x, from.y), Coordinates(to.x, from.y))
        lineAlgorithmInstance.compute(Coordinates(to.x, to.y), Coordinates(to.x, from.y))
        lineAlgorithmInstance.compute(Coordinates(from.x, to.y), Coordinates(from.x, from.y))
        lineAlgorithmInstance.compute(Coordinates(to.x, to.y), Coordinates(from.x, to.y))
    }

    fun compute(p1: Coordinates, p2: Coordinates) {
        var (cx, cy) = p1

        if (p1.x >= p2.x && p1.y <= p2.y) {
            while (cx >= p2.x) {
                for (i in cy..p2.y) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx--
            }
        } else if (p1.x <= p2.x && p1.y <= p2.y) {
            while (cx <= p2.x) {
                for (i in cy..p2.y) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx++
            }
        } else if (p1.x <= p2.x) {
            while (cx <= p2.x) {
                for (i in p2.y..cy) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx++
            }
        } else {
            while (cx >= p2.x) {
                for (i in p2.y..cy) {
                    algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(cx, i), algorithmInfo.color, true)
                }

                algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(p1, algorithmInfo.color, true)

                cx--
            }
        }
        if (borderColor != null) {
            drawBorder(p1, p2)
        }
    }
}