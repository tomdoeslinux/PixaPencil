package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions

class RectangleAlgorithm(private val instance: MyCanvasView, private val defaultRectPaint: Paint, private val rectangleData: List<RectF>, private val borderPaint: Paint? = null) {
    private fun drawBorder(from: XYPosition, to: XYPosition) {
        val lineAlgorithmInstance = LineAlgorithm(instance, borderPaint!!, rectangleData)
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
                instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
                instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)

                for (i in y..to.y) {
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())]] = defaultRectPaint
                    instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())], defaultRectPaint)
                }

                x--
            }

            if (borderPaint != null) {
                drawBorder(from, to)
            }
        } else if (from.x <= to.x && from.y <= to.y) {
            while (x <= to.x) {
                instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
                instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)

                for (i in y..to.y) {
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())]] = defaultRectPaint
                    instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())], defaultRectPaint)
                }

                x++
            }

            if (borderPaint != null) {
                drawBorder(from, to)
            }
        } else if (from.x <= to.x && from.y >= to.y) {
            while (x <= to.x) {
                instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
                instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)

                for (i in to.y..y) {
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())]] = defaultRectPaint
                    instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())], defaultRectPaint)
                }

                x++
            }

            if (borderPaint != null) {
                drawBorder(from, to)
            }
        } else if (from.x >= to.x && from.y >= to.y) {
            while (x >= to.x) {
                instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
                instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)

                for (i in to.y..y) {
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())]] = defaultRectPaint
                    instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, i), instance.spanCount.toInt())], defaultRectPaint)
                }

                x--
            }

            if (borderPaint != null) {
                drawBorder(from, to)
            }
        }
    }
}