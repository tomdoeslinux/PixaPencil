package com.realtomjoney.pyxlmoose.algorithms.shapes

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

class RectangleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val borderColor: Int? = null)
    : ShapeAlgorithm {
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

    override fun compute(p1: Coordinates, p2: Coordinates) {
        var x = p1.x
        val y = p1.y

        if (p1.x >= p2.x && p1.y <= p2.y) {
            while (x >= p2.x) {
                for (i in y..p2.y) {
                    algorithmInfo.currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        algorithmInfo.bitmap.getPixel(x, i),
                    ))
                    algorithmInfo.bitmap.setPixel(x, i, algorithmInfo.color)
                }

                algorithmInfo.bitmap.setPixel(x, y, algorithmInfo.color)

                x--
            }
        } else if (p1.x <= p2.x && p1.y <= p2.y) {
            while (x <= p2.x) {
                for (i in y..p2.y) {
                    algorithmInfo.currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        algorithmInfo.bitmap.getPixel(x, i),
                    ))
                    algorithmInfo.bitmap.setPixel(x, i, algorithmInfo.color)
                }

                algorithmInfo.bitmap.setPixel(x, y, algorithmInfo.color)

                x++
            }
        } else if (p1.x <= p2.x && p1.y >= p2.y) {
            while (x <= p2.x) {
                for (i in p2.y..y) {
                    algorithmInfo.currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        algorithmInfo.bitmap.getPixel(x, i),
                    ))
                    algorithmInfo.bitmap.setPixel(x, i, algorithmInfo.color)
                }

                algorithmInfo.bitmap.setPixel(x, y, algorithmInfo.color)

                x++
            }
        } else if (p1.x >= p2.x && p1.y >= p2.y) {
            while (x >= p2.x) {
                for (i in p2.y..y) {
                    algorithmInfo.currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        algorithmInfo.bitmap.getPixel(x, i),
                    ))
                    algorithmInfo.bitmap.setPixel(x, i, algorithmInfo.color)
                }

                algorithmInfo.bitmap.setPixel(x, y, algorithmInfo.color)

                x--
            }
        }
        if (borderColor != null) {
            drawBorder(p1, p2)
        }
    }
}