package com.therealbluepandabear.pixapencil.algorithms

import android.util.Log
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.models.Coordinates

/**
 * The code is based off of the following:
 *
 * - [GitHub Gist](https://gist.github.com/sahasayan/01653be282567b174c7dabb958b75104)
 * - [YouTube Video](https://www.youtube.com/watch?v=yfLm9nXsCvw)
 */

class MidpointCircleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val evenDiameterMode: Boolean = false, private val filledMode: Boolean = false) {
    private val shouldLineIgnoreBrush = true

    private fun putPixel(p1: Coordinates, p2: Coordinates) {
        val xc = p1.x
        val yc = p1.y

        val x = p2.x
        val y = p2.y

        if (!evenDiameterMode) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + x, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + y, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + x, yc + y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + y, yc + x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - y, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - y, yc + x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc + y),
                algorithmInfo.color
            )

            if (filledMode) {
                val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

                lineAlgorithmInstance.compute(
                    Coordinates(xc + x, yc - y),
                    Coordinates(xc - x, yc - y)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - y, yc + x),
                    Coordinates(xc + y, yc + x)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, yc + y),
                    Coordinates(xc + x, yc + y)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, yc + y),
                    Coordinates(xc + x, yc + y)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - y, yc - x),
                    Coordinates(xc + y, yc - x)
                )
            }
        } else {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + x) + 1, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + y) + 1, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + x) + 1, (yc + y) + 1),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + y) + 1, (yc + x) + 1),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc - y),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - y, yc - x),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - y, (yc + x) + 1),
                algorithmInfo.color
            )
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, (yc + y) + 1),
                algorithmInfo.color
            )

            if (filledMode) {
                val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

                lineAlgorithmInstance.compute(
                    Coordinates((xc + x) + 1, yc - y),
                    Coordinates(xc - x, yc - y)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - y, (yc + x) + 1),
                    Coordinates((xc + y) + 1, (yc + x) + 1)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, (yc + y) + 1),
                    Coordinates((xc + x) + 1, (yc + y) + 1)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, (yc + y) + 1),
                    Coordinates((xc + x) + 1, (yc + y) + 1)
                )
                lineAlgorithmInstance.compute(
                    Coordinates(xc - y, yc - x),
                    Coordinates((xc + y) + 1, yc - x)
                )
            }
        }
    }

    fun compute(p1: Coordinates, radius: Int) {
        Log.d("CIRCLE", "CIRCLE STARTED")
        val xc = p1.x
        val yc = p1.y

        var x = 0
        var y = radius

        var p = 1 - radius

        putPixel(Coordinates(xc, yc), Coordinates(x, y))

        while (x < y) {
            p = if (p < 0) {
                x++
                (p + 2 * x + 1)
            } else {
                x++
                y--
                (p + 2 * (x - y) + 1)
            }
            putPixel(Coordinates(xc, yc), Coordinates(x, y))
        }
    }
}