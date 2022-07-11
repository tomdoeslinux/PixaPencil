package com.therealbluepandabear.pixapencil.algorithms

import android.graphics.Color
import android.util.Log
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

class MidpointEllipseAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val xDEC: Boolean = false, private val yDEC: Boolean = false, private val filledMode: Boolean = false) {
    private val shouldLineIgnoreBrush = true

    private fun putPixel(p1: Coordinates, p2: Coordinates) {
        val xc = p1.x
        val yc = p1.y

        val x = p2.x
        val y = p2.y

        if (!xDEC && !yDEC) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + x, yc + y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + x, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc + y),
                algorithmInfo.color
            )

            if (filledMode) {
                val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

                lineAlgorithmInstance.compute(
                    Coordinates(xc + x, yc + y),
                    Coordinates(xc + x, yc - y),
                )

                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, yc - y),
                    Coordinates(xc - x, yc + y),
                )
            }
        } else if (xDEC && !yDEC) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + x) + 1, yc + y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + x) + 1, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc + y),
                algorithmInfo.color
            )

            if (filledMode) {
                val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

                lineAlgorithmInstance.compute(
                    Coordinates((xc + x) + 1, yc + y),
                    Coordinates((xc + x) + 1, yc - y),
                )

                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, yc - y),
                    Coordinates(xc - x, yc + y),
                )
            }
        } else if (!xDEC && yDEC) {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + x, (yc + y) + 1),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc + x, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, (yc + y) + 1),
                algorithmInfo.color
            )

            if (filledMode) {
                val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

                lineAlgorithmInstance.compute(
                    Coordinates(xc + x, (yc + y) + 1),
                    Coordinates(xc + x, yc - y),
                )

                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, yc - y),
                    Coordinates(xc - x, (yc + y) + 1),
                )
            }
        } else {
            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + x) + 1, (yc + y) + 1),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates((xc + x) + 1, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, yc - y),
                algorithmInfo.color
            )

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(
                Coordinates(xc - x, (yc + y) + 1),
                algorithmInfo.color
            )

            if (filledMode) {
                val lineAlgorithmInstance = LineAlgorithm(algorithmInfo, shouldLineIgnoreBrush)

                lineAlgorithmInstance.compute(
                    Coordinates((xc + x) + 1, (yc + y) + 1),
                    Coordinates((xc + x) + 1, yc - y),
                )

                lineAlgorithmInstance.compute(
                    Coordinates(xc - x, yc - y),
                    Coordinates(xc - x, (yc + y) + 1),
                )
            }
        }
    }

    fun compute(p1: Coordinates, rx: Int, ry: Int) {
        Log.d("ELLIPSE", "ELLIPSE")

        val idp = Coordinates(0, ry)

        var xkp1 = idp.x
        var ykp1 = idp.y

        var lxkp1: Int
        var lykp1: Int

        var p1k = (ry * ry) + ((rx * rx) / 4) - (ry * (rx * rx))

        val incy = p1.y
        val incx = p1.x

        putPixel(Coordinates(incx, incy), Coordinates(xkp1, ykp1))

        while (
            (2 * (xkp1 + 1) * (ry * ry))
            <
            (2 * ykp1 * (rx * rx))
        ) {
            p1k += if (p1k >= 0) {
                xkp1++
                ykp1--

                lxkp1 = xkp1 - 1
                lykp1 = ykp1 + 1

                (ry * ry) + (2 * (lxkp1 + 1)) * (ry * ry) + (rx * rx) * ((ykp1 * ykp1) - (lykp1 * lykp1)) - (rx * rx) * (ykp1 - lykp1)
            } else {
                xkp1++

                lxkp1 = xkp1 - 1
                lykp1 = ykp1

                (ry * ry) + (2 * (lxkp1 + 1)) * (ry * ry) + (rx * rx) * ((ykp1 * ykp1) - (lykp1 * lykp1))
            }

            putPixel(Coordinates(incx, incy), Coordinates(xkp1, ykp1))
        }

        var p2k = (ry.toDouble() * ry.toDouble()) * ((xkp1.toDouble() + 0.5) * (xkp1.toDouble() + 0.5)) + (rx.toDouble() * rx.toDouble()) * ((ykp1.toDouble() - 1) * (ykp1.toDouble() - 1)) - ((rx.toDouble() * rx.toDouble()) * (ry.toDouble() * ry.toDouble()))

        while (
            ykp1 > 0
        ) {
            if (p2k >= 0) {
                ykp1--
                lykp1 = ykp1 + 1
                lxkp1 = xkp1

                p2k += (rx * rx) - 2 * (rx * rx) * (lykp1 - 1) + (ry * ry) * ((xkp1 * xkp1) - (lxkp1 * lxkp1))
            } else {
                xkp1++
                lxkp1 = xkp1 - 1
                ykp1--
                lykp1 = ykp1 + 1

                p2k += (rx * rx) - 2 * (rx * rx) * (lykp1 - 1) + (ry * ry) * ((xkp1 * xkp1) - (lxkp1 * lxkp1)) + (ry * ry) * (xkp1 - lxkp1)
            }

            putPixel(Coordinates(incx, incy), Coordinates(xkp1, ykp1))
        }
    }
}
