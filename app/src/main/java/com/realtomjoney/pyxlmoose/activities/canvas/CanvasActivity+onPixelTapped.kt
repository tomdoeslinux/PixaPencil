package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt

fun expandToNeighbors(spanCount: Int, from: XYPosition): List<Int> {
    val toReturn = mutableListOf<Int>()

    if (from.x > 1) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x - 1, from.y), spanCount))

    if (from.x < spanCount) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x + 1, from.y), spanCount))

    if (from.y > 1) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x, from.y - 1), spanCount))

    if (from.y < spanCount) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x, from.y + 1), spanCount))

    return toReturn
}

fun drawLineY(from: XYPosition, to: XYPosition, instance: MyCanvasView, defaultRectPaint: Paint, rectangleData: List<RectF>) {
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
        instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)
        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
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

fun drawLineX(from: XYPosition, to: XYPosition, instance: MyCanvasView, defaultRectPaint: Paint, rectangleData: List<RectF>) {
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
        instance.extraCanvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())], defaultRectPaint)
        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(XYPosition(x, y), instance.spanCount.toInt())]] = defaultRectPaint
        y++

        if (p < 0) {
            p += 2 * differenceX
        } else {
            p = p + 2 * differenceX - 2 * differenceY
            x += xi
        }
    }
}

fun drawLine(from: XYPosition, to: XYPosition, instance: MyCanvasView, defaultRectPaint: Paint, rectangleData: List<RectF>) {
    val x = from.x
    val y = from.y

    val differenceX = to.x - x
    val differenceY = to.y - y

    if (differenceY <= differenceX) {
        if (abs(differenceY) > differenceX) {
            drawLineX(to, from, instance, defaultRectPaint, rectangleData)
        } else {
             drawLineY(from, to, instance, defaultRectPaint, rectangleData)
        }
    } else if (differenceX < differenceY) {
        if (abs(differenceX) > differenceY) {
            drawLineY(to, from, instance, defaultRectPaint, rectangleData)
        } else {
            drawLineX(from, to, instance, defaultRectPaint, rectangleData)
        }
    }
}

var lineOrigin: XYPosition? = null

fun CanvasActivity.extendedOnPixelTapped(instance: MyCanvasView, rectTapped: RectF) {
    saved = false

    val defaultErasePaint = Paint().apply {
        style = Paint.Style.FILL
        color = currentBackground ?: Color.WHITE
        isAntiAlias = false
    }

    val defaultRectPaint =  Paint().apply {
        style = Paint.Style.FILL
        color = getSelectedColor()
        isAntiAlias = false
    }

    deletedCanvasStates.clear()

    val rectangleData = instance.rectangles.keys.toList()

    when (currentTool) {
        Tools.PENCIL_TOOL -> {
                instance.rectangles[rectTapped] = defaultRectPaint

                instance.extraCanvas.apply {
                    drawRect(rectTapped, defaultRectPaint)

                    if (currentBrush != null) {
                        val xyData = MathExtensions.convertIndexToXYPosition(
                            instance.rectangles.keys.toList().indexOf(rectTapped),
                            instance.spanCount.toInt()
                        )
                        for (xyData in currentBrush!!.convertBrushInstructionDataToXYPositionData(
                            instance.spanCount.toInt(),
                            xyData
                        )) {
                            instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                                xyData,
                                instance.spanCount.toInt()
                            )]] = defaultRectPaint
                            drawRect(
                                instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                                    xyData,
                                    instance.spanCount.toInt()
                                )], defaultRectPaint
                            )
                        }
                    }
                }
        }
        Tools.FILL_TOOL -> {
            val seedColor = instance.rectangles[rectTapped]?.color ?: Color.WHITE

            val queue = LinkedList<XYPosition>()

            val spanCount = instance.spanCount.toInt()

            queue.offer(
                MathExtensions.convertIndexToXYPosition(
                    rectangleData.indexOf(rectTapped),
                    spanCount
                )
            )

            val selectedColor = getSelectedColor()

            while (queue.isNotEmpty() && seedColor != selectedColor) {
                val current = queue.poll()

                val color =
                    instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(
                        current,
                        spanCount
                    )]]?.color ?: Color.WHITE

                if (color != seedColor) {
                    continue
                }

                instance.rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(
                    current,
                    spanCount
                )]] = defaultRectPaint // Colors in pixel with defaultRectPaint
                instance.extraCanvas.drawRect(
                    rectangleData[MathExtensions.convertXYPositionToIndex(
                        current,
                        spanCount
                    )], defaultRectPaint
                )

                for (index in expandToNeighbors(spanCount, current)) {
                    val candidate = MathExtensions.convertIndexToXYPosition(index, spanCount)
                    queue.offer(candidate)
                }
            }
        }
        Tools.HORIZONTAL_MIRROR_TOOL -> {
            instance.extraCanvas.apply {
                val horizontallyReflectedIndex = MathExtensions.reflectIndexVertically(
                    rectangleData.indexOf(rectTapped),
                    instance.spanCount.toInt()
                )

                instance.rectangles[rectTapped] = defaultRectPaint
                instance.rectangles[rectangleData[horizontallyReflectedIndex]] =
                    defaultRectPaint

                drawRect(rectTapped, defaultRectPaint)
                drawRect(rectangleData[horizontallyReflectedIndex], defaultRectPaint)

                if (currentBrush != null) {
                    val xyData = MathExtensions.convertIndexToXYPosition(
                        instance.rectangles.keys.toList().indexOf(rectTapped),
                        instance.spanCount.toInt()
                    )
                    for (xyData in currentBrush!!.convertBrushInstructionDataToXYPositionData(
                        instance.spanCount.toInt(),
                        xyData
                    )) {
                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                            xyData,
                            instance.spanCount.toInt()
                        )]] = defaultRectPaint
                        drawRect(
                            instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                                xyData,
                                instance.spanCount.toInt()
                            )], defaultRectPaint
                        )

                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.reflectIndexVertically(
                            MathExtensions.convertXYPositionToIndex(
                                xyData,
                                instance.spanCount.toInt()
                            ),
                            instance.spanCount.toInt()
                        )]] = defaultRectPaint
                        drawRect(
                            instance.rectangles.keys.toList()[MathExtensions.reflectIndexVertically(
                                MathExtensions.convertXYPositionToIndex(
                                    xyData,
                                    instance.spanCount.toInt()
                                ),
                                instance.spanCount.toInt()
                            )], defaultRectPaint
                        )
                    }
                }
            }
        }
        Tools.VERTICAL_MIRROR_TOOL -> {
            instance.extraCanvas.apply {
                val verticallyReflectedIndex = MathExtensions.reflectIndexHorizontally(
                    rectangleData.indexOf(rectTapped),
                    sqrt(instance.rectangles.keys.size.toDouble()).toInt()
                )

                instance.rectangles[rectTapped] = defaultRectPaint
                instance.rectangles[rectangleData[verticallyReflectedIndex]] = defaultRectPaint

                drawRect(rectTapped, defaultRectPaint)
                drawRect(rectangleData[verticallyReflectedIndex], defaultRectPaint)

                if (currentBrush != null) {
                    val xyData = MathExtensions.convertIndexToXYPosition(
                        instance.rectangles.keys.toList().indexOf(rectTapped),
                        instance.spanCount.toInt()
                    )
                    for (xyData in currentBrush!!.convertBrushInstructionDataToXYPositionData(
                        instance.spanCount.toInt(),
                        xyData
                    )) {
                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                            xyData,
                            instance.spanCount.toInt()
                        )]] = defaultRectPaint
                        drawRect(
                            instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                                xyData,
                                instance.spanCount.toInt()
                            )], defaultRectPaint
                        )

                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.reflectIndexHorizontally(
                            MathExtensions.convertXYPositionToIndex(
                                xyData,
                                instance.spanCount.toInt()
                            ),
                            instance.spanCount.toInt()
                        )]] = defaultRectPaint
                        drawRect(
                            instance.rectangles.keys.toList()[MathExtensions.reflectIndexHorizontally(
                                MathExtensions.convertXYPositionToIndex(
                                    xyData,
                                    instance.spanCount.toInt()
                                ),
                                instance.spanCount.toInt()
                            )], defaultRectPaint
                        )
                    }
                }
            }
        }
        Tools.LINE_TOOL -> {
            if (!lineMode_hasLetGo) {
                extendedUndo()
            } else {
                lineMode_hasLetGo = false
            }

            if (lineOrigin == null) {
                lineOrigin = MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), spanCount)
            } else {
                drawLine(lineOrigin!!, MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), spanCount), canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData)
            }

            canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
        }
        Tools.COLOR_PICKER_TOOL -> {
            if (instance.rectangles[rectTapped] == null) setPixelColor(Color.WHITE) else setPixelColor(
                instance.rectangles[rectTapped]!!.color
            )
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

                if (currentBrush != null) {
                    val xyData = MathExtensions.convertIndexToXYPosition(
                        instance.rectangles.keys.toList().indexOf(rectTapped),
                        instance.spanCount.toInt()
                    )
                    for (xyData in currentBrush!!.convertBrushInstructionDataToXYPositionData(
                        instance.spanCount.toInt(),
                        xyData
                    )) {
                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                            xyData,
                            instance.spanCount.toInt()
                        )]] = defaultErasePaint
                        drawRect(
                            instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(
                                xyData,
                                instance.spanCount.toInt()
                            )], defaultErasePaint
                        )
                    }
                }
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