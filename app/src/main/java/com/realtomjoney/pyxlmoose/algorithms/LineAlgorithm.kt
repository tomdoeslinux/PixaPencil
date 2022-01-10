package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Paint
import android.graphics.RectF
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.models.Brush
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import kotlin.math.abs

class LineAlgorithm(private val instance: MyCanvasView, private val defaultRectPaint: Paint, private val rectangleData: List<RectF>, val brush: Brush? = null) {
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
            instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
            instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)

            if (brush != null) {
                for (xyData in brush.convertBrushInstructionDataToXYPositionData(instance.spanCount.toInt(), XYPosition(x, y))) {
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())]] = defaultRectPaint
                    instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())], defaultRectPaint)
                }
            }

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
            instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
            instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)

            if (brush != null) {
                for (xyData in brush.convertBrushInstructionDataToXYPositionData(instance.spanCount.toInt(), XYPosition(x, y))) {
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())]] = defaultRectPaint
                    instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())], defaultRectPaint)
                }
            }


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