package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*
import com.realtomjoney.pyxlmoose.algorithms.ExpandToNeighborsAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.LineAlgorithm
import com.realtomjoney.pyxlmoose.algorithms.RectangleAlgorithm
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import java.util.*

var lineOrigin: XYPosition? = null

var rectangleOrigin: XYPosition? = null

var expandToNeighborsAlgorithmInstance = ExpandToNeighborsAlgorithm()

var pixelsTappedAsXYPosition = mutableListOf<XYPosition>()

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

    val borderRectanglePaint = Paint().apply {
        style = Paint.Style.FILL
        color = secondaryColor
        isAntiAlias = false
    }


    deletedCanvasStates.clear()

    val rectangles = canvasFragmentInstance.myCanvasViewInstance.rectangles
    val canvas = canvasFragmentInstance.myCanvasViewInstance.extraCanvas
    val rectangleData = canvasFragmentInstance.myCanvasViewInstance.rectangles.keys.toList()
    val spanCount = canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt()
    val pixelTappedAsXYPosition = MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), spanCount)
    val currentBrushInstructionData = currentBrush?.convertBrushInstructionDataToXYPositionData(spanCount, pixelTappedAsXYPosition)
    val horizontallyReflectedIndex = MathExtensions.reflectIndexVertically(rectangleData.indexOf(rectTapped), spanCount)
    val verticallyReflectedIndex = MathExtensions.reflectIndexHorizontally(rectangleData.indexOf(rectTapped), spanCount)

    when (currentTool) {
        Tools.PENCIL_TOOL -> {
            pixelsTappedAsXYPosition.add(pixelTappedAsXYPosition)

            rectangles[rectTapped] = defaultRectPaint
            canvas.drawRect(rectTapped, defaultRectPaint)

            val lineAlgorithmInstance = LineAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData, currentBrush)

            if (pixelsTappedAsXYPosition.size > 1)
                lineAlgorithmInstance.compute(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 2], pixelsTappedAsXYPosition.last())

            if (currentBrush != null) {
                for (xyData in currentBrushInstructionData!!) {
                    rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)]] = defaultRectPaint
                    canvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)], defaultRectPaint)
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

                for (index in expandToNeighborsAlgorithmInstance.compute(spanCount, current)) {
                    val candidate = MathExtensions.convertIndexToXYPosition(index, spanCount)
                    queue.offer(candidate)
                }
            }
        }
        Tools.HORIZONTAL_MIRROR_TOOL -> {
            pixelsTappedAsXYPosition.add(pixelTappedAsXYPosition)

            rectangles[rectTapped] = defaultRectPaint
            rectangles[rectangleData[horizontallyReflectedIndex]] = defaultRectPaint

            canvas.drawRect(rectTapped, defaultRectPaint)
            canvas.drawRect(rectangleData[horizontallyReflectedIndex], defaultRectPaint)

            val lineAlgorithmInstance = LineAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData, currentBrush)

            if (pixelsTappedAsXYPosition.size > 1) {
                lineAlgorithmInstance.compute(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 2], pixelsTappedAsXYPosition.last())
                lineAlgorithmInstance.compute(MathExtensions.convertIndexToXYPosition(MathExtensions.reflectIndexVertically(MathExtensions.convertXYPositionToIndex(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 2], spanCount), spanCount), spanCount), MathExtensions.convertIndexToXYPosition(MathExtensions.reflectIndexVertically(MathExtensions.convertXYPositionToIndex(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 1], spanCount), spanCount), spanCount))
            }

            if (currentBrush != null) {
                for (xyData in currentBrushInstructionData!!) {
                    rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)]] = defaultRectPaint
                    rectangles[rectangleData[MathExtensions.reflectIndexVertically(MathExtensions.convertXYPositionToIndex(xyData, spanCount), spanCount)]] = defaultRectPaint
                    canvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)], defaultRectPaint)
                    canvas.drawRect(rectangleData[MathExtensions.reflectIndexVertically(MathExtensions.convertXYPositionToIndex(xyData, spanCount), spanCount)], defaultRectPaint)
                }
            }
        }
        Tools.VERTICAL_MIRROR_TOOL -> {
            pixelsTappedAsXYPosition.add(pixelTappedAsXYPosition)

            rectangles[rectTapped] = defaultRectPaint
            rectangles[rectangleData[verticallyReflectedIndex]] = defaultRectPaint

            canvas.drawRect(rectTapped, defaultRectPaint)
            canvas.drawRect(rectangleData[verticallyReflectedIndex], defaultRectPaint)

            val lineAlgorithmInstance = LineAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData, currentBrush)

            if (pixelsTappedAsXYPosition.size > 1) {
                lineAlgorithmInstance.compute(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 2], pixelsTappedAsXYPosition.last())
                lineAlgorithmInstance.compute(MathExtensions.convertIndexToXYPosition(MathExtensions.reflectIndexHorizontally(MathExtensions.convertXYPositionToIndex(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 2], spanCount), spanCount), spanCount), MathExtensions.convertIndexToXYPosition(MathExtensions.reflectIndexHorizontally(MathExtensions.convertXYPositionToIndex(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 1], spanCount), spanCount), spanCount))
            }

            if (currentBrush != null) {
                for (xyData in currentBrushInstructionData!!) {
                    rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)]] = defaultRectPaint
                    rectangles[rectangleData[MathExtensions.reflectIndexHorizontally(MathExtensions.convertXYPositionToIndex(xyData, spanCount), spanCount)]] = defaultRectPaint
                    canvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)], defaultRectPaint)
                    canvas.drawRect(rectangleData[MathExtensions.reflectIndexHorizontally(MathExtensions.convertXYPositionToIndex(xyData, spanCount), spanCount)], defaultRectPaint)
                }
            }

        }
        Tools.LINE_TOOL -> {
            val lineAlgorithmInstance = LineAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData, currentBrush)

            if (!lineMode_hasLetGo) extendedUndo() else lineMode_hasLetGo = false

            if (lineOrigin == null) {
                lineOrigin = MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt())
            } else {
                lineAlgorithmInstance.compute(lineOrigin!!, MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt()))
            }

            canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
        }
        Tools.RECTANGLE_TOOL -> {
            val rectangleAlgorithmInstance = RectangleAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData)

            if (!rectangleMode_hasLetGo) extendedUndo() else rectangleMode_hasLetGo = false

            if (rectangleOrigin == null) {
                rectangleOrigin = MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt())
            } else {
                rectangleAlgorithmInstance.compute(rectangleOrigin!!, MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt()))
            }

            canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
        }
        Tools.OUTLINED_RECTANGLE_TOOL -> {
            val rectangleAlgorithmInstance = RectangleAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultRectPaint, rectangleData, borderRectanglePaint)

            if (!rectangleMode_hasLetGo) extendedUndo() else rectangleMode_hasLetGo = false

            if (rectangleOrigin == null) {
                rectangleOrigin = MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt())
            } else {
                rectangleAlgorithmInstance.compute(rectangleOrigin!!, MathExtensions.convertIndexToXYPosition(rectangleData.indexOf(rectTapped), canvasFragmentInstance.myCanvasViewInstance.spanCount.toInt()))
            }

            canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
        }
        Tools.COLOR_PICKER_TOOL -> {
            if (instance.rectangles[rectTapped] == null) setPixelColor(Color.WHITE) else setPixelColor(
                instance.rectangles[rectTapped]!!.color
            )
        }
        Tools.ERASE_TOOL -> {
            pixelsTappedAsXYPosition.add(pixelTappedAsXYPosition)

            rectangles[rectTapped] = defaultErasePaint
            canvas.drawRect(rectTapped, defaultErasePaint)

            val lineAlgorithmInstance = LineAlgorithm(canvasFragmentInstance.myCanvasViewInstance, defaultErasePaint, rectangleData, currentBrush)

            if (pixelsTappedAsXYPosition.size > 1)
                lineAlgorithmInstance.compute(pixelsTappedAsXYPosition[pixelsTappedAsXYPosition.size - 2], pixelsTappedAsXYPosition.last())

            if (currentBrush != null) {
                for (xyData in currentBrushInstructionData!!) {
                    rectangles[rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)]] = defaultErasePaint
                    canvas.drawRect(rectangleData[MathExtensions.convertXYPositionToIndex(xyData, spanCount)], defaultErasePaint)
                }
            }
        }
    }

}