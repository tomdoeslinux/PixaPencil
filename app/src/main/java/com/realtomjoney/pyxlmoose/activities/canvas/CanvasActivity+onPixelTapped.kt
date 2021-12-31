package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.enums.BrushInstruction
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import java.util.*
import kotlin.math.sqrt

fun expandToNeighbors(spanCount: Int, from: XYPosition): List<Int> {
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

fun convertBrushInstructionToXYPosition(spanCount: Int, from: XYPosition, brushInstructions: List<BrushInstruction>): MutableList<XYPosition> {
    val instructions = mutableListOf<XYPosition>()

    for (brushInstruction in brushInstructions) {
        when (brushInstruction) {
            BrushInstruction.EXPAND_TOP -> {
                if (from.y < spanCount) {
                    instructions.add(XYPosition(from.x, from.y + 1))
                }
            }
            BrushInstruction.EXPAND_BOTTOM -> {
                if (from.y > 1) {
                    instructions.add(XYPosition(from.x, from.y - 1))
                }
            }
            BrushInstruction.EXPAND_LEFT -> {
                if (from.x > 1) {
                    instructions.add(XYPosition(from.x - 1, from.y))
                }
            }
            BrushInstruction.EXPAND_RIGHT -> {
                if (from.x < spanCount) {
                    instructions.add(XYPosition(from.x + 1, from.y))
                }
            }

            BrushInstruction.EXPAND_TOP_RIGHT -> {
                if (from.x < spanCount && from.y < spanCount) {
                    instructions.add(XYPosition(from.x + 1, from.y + 1))
                }
            }
            BrushInstruction.EXPAND_TOP_LEFT -> {
                if (from.x > 1 && from.y < spanCount) {
                    instructions.add(XYPosition(from.x - 1, from.y + 1))
                }
            }
            BrushInstruction.EXPAND_BOTTOM_RIGHT -> {
                if (from.x > 1 && from.y > 1) {
                    instructions.add(XYPosition(from.x - 1, from.y - 1))
                }
            }
            BrushInstruction.EXPAND_BOTTOM_LEFT -> {
                if (from.x < spanCount && from.y > 1) {
                    instructions.add(XYPosition(from.x + 1, from.y - 1))
                }
            }

            BrushInstruction.EXPAND_ALL -> {
                if (from.y < spanCount) {
                    instructions.add(XYPosition(from.x, from.y + 1))
                }
                if (from.y > 1) {
                    instructions.add(XYPosition(from.x, from.y - 1))
                }
                if (from.x > 1) {
                    instructions.add(XYPosition(from.x - 1, from.y))
                }
                if (from.x < spanCount) {
                    instructions.add(XYPosition(from.x + 1, from.y))
                }
                if (from.x < spanCount && from.y < spanCount) {
                    instructions.add(XYPosition(from.x + 1, from.y + 1))
                }
                if (from.x > 1 && from.y < spanCount) {
                    instructions.add(XYPosition(from.x - 1, from.y + 1))
                }

                if (from.x > 1 && from.y > 1) {
                    instructions.add(XYPosition(from.x - 1, from.y - 1))
                }

                if (from.x < spanCount && from.y > 1) {
                    instructions.add(XYPosition(from.x + 1, from.y - 1))
                }
            }
        }
    }

    return instructions
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

    when (currentTool) {
        Tools.PENCIL_TOOL -> {
            instance.rectangles[rectTapped] = defaultRectPaint

            instance.extraCanvas.apply {
                drawRect(rectTapped, defaultRectPaint)

                if (currentBrush != null) {
                    val xyData = MathExtensions.convertIndexToXYPosition(instance.rectangles.keys.toList().indexOf(rectTapped), instance.spanCount.toInt())
                    for (xyData in convertBrushInstructionToXYPosition(instance.spanCount.toInt(), xyData, currentBrush!!.brushInstructions)) {
                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())]] = defaultRectPaint
                        drawRect(instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())], defaultRectPaint)
                    }
                }
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

                for (index in expandToNeighbors(spanCount, current)) {
                    val candidate = MathExtensions.convertIndexToXYPosition(index, spanCount)
                    queue.offer(candidate)
                }
            }
        }
        Tools.HORIZONTAL_MIRROR_TOOL -> {
            instance.extraCanvas.apply {
                val horizontallyReflectedIndex = MathExtensions.reflectIndexVertically(rectangleData.indexOf(rectTapped), instance.spanCount.toInt())

                instance.rectangles[rectTapped] = defaultRectPaint
                instance.rectangles[rectangleData[horizontallyReflectedIndex]] =
                    defaultRectPaint

                drawRect(rectTapped, defaultRectPaint)
                drawRect(rectangleData[horizontallyReflectedIndex], defaultRectPaint)

                if (currentBrush != null) {
                    val xyData = MathExtensions.convertIndexToXYPosition(instance.rectangles.keys.toList().indexOf(rectTapped), instance.spanCount.toInt())
                    for (xyData in convertBrushInstructionToXYPosition(instance.spanCount.toInt(), xyData, currentBrush!!.brushInstructions)) {
                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())]] = defaultRectPaint
                        drawRect(instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())], defaultRectPaint)

                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.reflectIndexVertically(MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt()), instance.spanCount.toInt())]] = defaultRectPaint
                        drawRect(instance.rectangles.keys.toList()[MathExtensions.reflectIndexVertically(MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt()), instance.spanCount.toInt())], defaultRectPaint)
                    }
                }
            }
        }
        Tools.VERTICAL_MIRROR_TOOL -> {
            instance.extraCanvas.apply {
                val verticallyReflectedIndex = MathExtensions.reflectIndexHorizontally(rectangleData.indexOf(rectTapped), sqrt(instance.rectangles.keys.size.toDouble()).toInt())

                instance.rectangles[rectTapped] = defaultRectPaint
                instance.rectangles[rectangleData[verticallyReflectedIndex]] = defaultRectPaint

                drawRect(rectTapped, defaultRectPaint)
                drawRect(rectangleData[verticallyReflectedIndex], defaultRectPaint)

                if (currentBrush != null) {
                    val xyData = MathExtensions.convertIndexToXYPosition(instance.rectangles.keys.toList().indexOf(rectTapped), instance.spanCount.toInt())
                    for (xyData in convertBrushInstructionToXYPosition(instance.spanCount.toInt(), xyData, currentBrush!!.brushInstructions)) {
                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())]] = defaultRectPaint
                        drawRect(instance.rectangles.keys.toList()[MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt())], defaultRectPaint)

                        instance.rectangles[instance.rectangles.keys.toList()[MathExtensions.reflectIndexHorizontally(MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt()), instance.spanCount.toInt())]] = defaultRectPaint
                        drawRect(instance.rectangles.keys.toList()[MathExtensions.reflectIndexHorizontally(MathExtensions.convertXYPositionToIndex(xyData, instance.spanCount.toInt()), instance.spanCount.toInt())], defaultRectPaint)
                    }
                }
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