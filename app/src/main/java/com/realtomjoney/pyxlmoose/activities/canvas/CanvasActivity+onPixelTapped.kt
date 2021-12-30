package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*
import android.util.Log
import android.widget.Toast
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import java.util.*
import kotlin.math.sqrt

var totalTime = 0.0
var totalTimeForExpandToNeighbors = 0.0
var totalTimeForDrawingRectData = 0.0
var loopTime = 0.0
var pollTime = 0.0

fun expandToNeighborsWithMap(spanCount: Int, from: XYPosition): List<Int> {
    val toReturn = mutableListOf<Int>()

    if (from.x > 1) {
        toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x - 1, from.y), spanCount))
    }

    if (from.x < spanCount) {
        toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x + 1, from.y), spanCount))
    }

    if (from.y > 1) {
        toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x, from.y - 1), spanCount))
    }

    if (from.y < spanCount) {
        toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x, from.y + 1), spanCount))
    }

    return toReturn
}

fun convertXYDataToIndex(spanCount: Int, from: XYPosition): Int {
    return MathExtensions.convertXYPositionToIndex(from, spanCount)
}

fun CanvasActivity.extendedOnPixelTapped(instance: MyCanvasView, rectTapped: RectF) {
    saved = false

    val defaultErasePaint = Paint().apply {
        style = Paint.Style.FILL
        color = currentBackground ?: Color.WHITE
    }

    val defaultRectPaint =  Paint().apply {
        style = Paint.Style.FILL
        color = getSelectedColor()
        isAntiAlias = false
    }

    val rectangleData = instance.rectangles.keys.toList()

    val startTime = System.currentTimeMillis()
    when (currentTool) {
        Tools.PENCIL_TOOL -> {
            instance.rectangles[rectTapped] = defaultRectPaint
            instance.extraCanvas.apply {
                drawRect(rectTapped, defaultRectPaint)
            }
        }
        Tools.FILL_TOOL -> {
            val seedColor = instance.rectangles[rectTapped]?.color ?: Color.WHITE

            val queue = LinkedList<XYPosition>()

            val spanCount = instance.spanCount.toInt()

            queue.offer(MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), spanCount))

            val selectedColor = getSelectedColor()

            while (queue.isNotEmpty() && seedColor != selectedColor) {

                val current = queue.poll()

                val color = instance.rectangles[rectangleData[convertXYDataToIndex(spanCount, current)]]?.color ?: Color.WHITE

                if (color != seedColor) {
                    continue
                }

                instance.rectangles[rectangleData[convertXYDataToIndex(spanCount, current)]] = defaultRectPaint // Colors in pixel with defaultRectPaint
                instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(current, spanCount)], defaultRectPaint)

                for (index in expandToNeighborsWithMap(spanCount, current)) {
                    val candidate = MathExtensions.convertIndexToXYPosition(index, spanCount)
                    queue.offer(candidate)
                }
            }
            val timeTakenForThis = (System.currentTimeMillis()-startTime)
            totalTime += timeTakenForThis
        }
        Tools.HORIZONTAL_MIRROR_TOOL -> {
            instance.extraCanvas.apply {
                val horizontallyReflectedIndex = MathExtensions.reflectIndexVertically(rectangleData.indexOf(rectTapped), sqrt(instance.rectangles.keys.size.toDouble()).toInt())

                instance.rectangles[rectTapped] = defaultRectPaint
                instance.rectangles[rectangleData[horizontallyReflectedIndex]] =
                    defaultRectPaint

                drawRect(rectTapped, defaultRectPaint)
                drawRect(rectangleData[horizontallyReflectedIndex], defaultRectPaint)
            }
        }
        Tools.VERTICAL_MIRROR_TOOL -> {
            instance.extraCanvas.apply {
                val verticallyReflectedIndex = MathExtensions.reflectIndexHorizontally(rectangleData.indexOf(rectTapped), sqrt(instance.rectangles.keys.size.toDouble()).toInt())

                instance.rectangles[rectTapped] = defaultRectPaint
                instance.rectangles[rectangleData[verticallyReflectedIndex]] = defaultRectPaint

                drawRect(rectTapped, defaultRectPaint)
                drawRect(rectangleData[verticallyReflectedIndex], defaultRectPaint)
            }
        }
        Tools.COLOR_PICKER_TOOL -> {
            if (instance.rectangles[rectTapped] == null) setPixelColor(Color.WHITE) else setPixelColor(instance.rectangles[rectTapped]!!.color)
        }
        Tools.CHANGE_BACKGROUND_TOOL -> {
            instance.extraCanvas.drawColor(getSelectedColor())
            currentBackground = getSelectedColor()

            for (pair in instance.rectangles) {
                pair.setValue(Paint().apply {
                    style = Paint.Style.FILL
                    color = getSelectedColor()
                })
            }
        }
        Tools.ERASE_TOOL -> {
            instance.rectangles[rectTapped] = defaultErasePaint
            instance.extraCanvas.apply {
                drawRect(rectTapped, defaultErasePaint)
            }
        }
        else -> {
            instance.rectangles[rectTapped] = defaultRectPaint
            instance.extraCanvas.apply {
                drawRect(rectTapped, defaultRectPaint)
            }
        }
    }
}