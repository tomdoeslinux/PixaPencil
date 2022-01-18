package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition

class RectangleAlgorithm(private val bitmap: Bitmap, private val currentBitmapAction: BitmapAction, private val color: Int = Color.BLACK, private val borderColor: Int? = null) {
    private fun drawBorder(from: XYPosition, to: XYPosition) {
        val lineAlgorithmInstance = LineAlgorithm(bitmap, currentBitmapAction, borderColor!!)
        lineAlgorithmInstance.compute(XYPosition(from.x, from.y), XYPosition(to.x, from.y))
        lineAlgorithmInstance.compute(XYPosition(to.x, to.y), XYPosition(to.x, from.y))
        lineAlgorithmInstance.compute(XYPosition(from.x, to.y), XYPosition(from.x, from.y))
        lineAlgorithmInstance.compute(XYPosition(to.x, to.y), XYPosition(from.x, to.y))
    }

    fun compute(from: XYPosition, to: XYPosition) {
        var x = from.x
        val y = from.y

        if (from.x >= to.x && from.y <= to.y) {
            while (x >= to.x) {
                for (i in y..to.y) {
                    currentBitmapAction.actionData.add(BitmapActionData(
                        XYPosition(x, i),
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
                        XYPosition(x, i),
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
                        XYPosition(x, i),
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
                        XYPosition(x, i),
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