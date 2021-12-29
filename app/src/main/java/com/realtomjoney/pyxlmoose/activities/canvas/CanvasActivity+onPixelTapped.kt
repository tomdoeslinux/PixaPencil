package com.realtomjoney.pyxlmoose.activities.canvas

import android.R
import android.graphics.*
import android.os.Handler
import android.util.Log
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import java.util.*
import kotlin.math.sqrt

//fun expandToNeighbors(instance: MyCanvasView, from: XYPosition): List<Int> {
//    var asIndex1 = from.x
//    var asIndex2 = from.x
//
//    var asIndex3 = from.y
//    var asIndex4 = from.y
//
//    if (from.x > 1) {
//        asIndex1 = xyPositionData!!.indexOf(XYPosition(from.x - 1, from.y))
//    }
//
//    if (from.x < instance.spanCount) {
//        asIndex2 = xyPositionData!!.indexOf(XYPosition(from.x + 1, from.y))
//    }
//
//    if (from.y > 1) {
//        asIndex3 = xyPositionData!!.indexOf(XYPosition(from.x, from.y - 1))
//    }
//
//    if (from.y < instance.spanCount) {
//        asIndex4 = xyPositionData!!.indexOf(XYPosition(from.x, from.y + 1))
//    }
//
//    return listOf(asIndex1, asIndex2, asIndex3, asIndex4)
//}

fun expandToNeighborsWithMap(instance: MyCanvasView, from: XYPosition): List<Int> {
    val toReturn = mutableListOf<Int>()

    if (from.x > 1) {
        toReturn.add(rectangleDataMap!![XYPosition(from.x - 1, from.y)]!!)
    }

    if (from.x < instance.spanCount) {
        toReturn.add(rectangleDataMap!![XYPosition(from.x + 1, from.y)]!!)
    }

    if (from.y > 1) {
        toReturn.add(rectangleDataMap!![XYPosition(from.x, from.y - 1)]!!)
    }

    if (from.y < instance.spanCount) {
        toReturn.add(rectangleDataMap!![XYPosition(from.x, from.y + 1)]!!)
    }

    return toReturn
}

var xyPositionData: List<XYPosition>? = null
var rectangleData: List<RectF>? = null
var rectangleDataMap: Map<XYPosition, Int>? = null

fun convertXYDataToIndex(instance: MyCanvasView, from: XYPosition): Int {

    if (rectangleData == null) {
        rectangleData = instance.rectangles.keys.toList()
    }

    if (xyPositionData == null) {
        xyPositionData = MathExtensions.convertListOfSizeNToListOfXYPosition(
            rectangleData!!.size,
            instance.spanCount.toInt()
        )
    }

    if (rectangleDataMap == null) {
        rectangleDataMap = MathExtensions.convertListToMap(
            rectangleData!!.size,
            instance.spanCount.toInt()
        )
    }

    return xyPositionData!!.indexOf(from)
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

    var totalTimeTaken = 0.0
    var totalTimeTakenForExpandToNeighbors = 0.0
    var totalTimeTakenForMath = 0.0

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

            queue.offer(MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), instance.spanCount.toInt()))

            val selectedColor = getSelectedColor()

            while (queue.isNotEmpty() && seedColor != selectedColor) { // While the queue is not empty the code below will run
                val current = queue.poll()
                val color = instance.rectangles.toList()[convertXYDataToIndex(instance, current)].second?.color ?: Color.WHITE

                if (color != seedColor) {
                    continue
                }

                instance.extraCanvas.apply {
                    instance.rectangles[rectangleData[convertXYDataToIndex(instance, current)]] = defaultRectPaint // Colors in pixel with defaultRectPaint
                    drawRect(rectangleData[convertXYDataToIndex(instance, current)], defaultRectPaint)

                    for (index in expandToNeighborsWithMap(instance, current)) {
                        val candidate = MathExtensions.convertIndexToXYPosition(index, instance.spanCount.toInt())
                        queue.offer(candidate)
                    }
                }
            }
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