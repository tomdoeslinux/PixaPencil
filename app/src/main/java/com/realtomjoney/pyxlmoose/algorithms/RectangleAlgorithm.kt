package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates

class RectangleAlgorithm(private val bitmap: Bitmap, private val currentBitmapAction: BitmapAction, private val color: Int = Color.BLACK, private val borderColor: Int? = null) {
    private fun drawBorder(from: Coordinates, to: Coordinates) {
        val lineAlgorithmInstance = LineAlgorithm(bitmap, currentBitmapAction, borderColor!!)
        lineAlgorithmInstance.compute(Coordinates(from.x, from.y), Coordinates(to.x, from.y))
        lineAlgorithmInstance.compute(Coordinates(to.x, to.y), Coordinates(to.x, from.y))
        lineAlgorithmInstance.compute(Coordinates(from.x, to.y), Coordinates(from.x, from.y))
        lineAlgorithmInstance.compute(Coordinates(to.x, to.y), Coordinates(from.x, to.y))
    }

    fun compute(from: Coordinates, to: Coordinates) {
        var x = from.x
        val y = from.y

        if (from.x >= to.x && from.y <= to.y) {
            while (x >= to.x) {
                for (i in y..to.y) {
                    currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        bitmap.getPixel(x, i),
                    ))
                    bitmap.setPixel(x, i, color)
                }

                bitmap.setPixel(x, y, color)

                x--
            }
        } else if (from.x <= to.x && from.y <= to.y) {
            while (x <= to.x) {
                for (i in y..to.y) {
                    currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        bitmap.getPixel(x, i),
                    ))
                    bitmap.setPixel(x, i, color)
                }

                bitmap.setPixel(x, y, color)

                x++
            }
        } else if (from.x <= to.x && from.y >= to.y) {
            while (x <= to.x) {

                for (i in to.y..y) {
                    currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        bitmap.getPixel(x, i),
                    ))
                    bitmap.setPixel(x, i, color)
                }

                bitmap.setPixel(x, y, color)

                x++
            }
        } else if (from.x >= to.x && from.y >= to.y) {
            while (x >= to.x) {

                for (i in to.y..y) {
                    currentBitmapAction.actionData.add(BitmapActionData(
                        Coordinates(x, i),
                        bitmap.getPixel(x, i),
                    ))
                    bitmap.setPixel(x, i, color)
                }

                bitmap.setPixel(x, y, color)

                x--
            }
        }
        if (borderColor != null) {
            drawBorder(from, to)
        }
    }
}