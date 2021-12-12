package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.*
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.utility.MathExtensions
import kotlin.math.sqrt

fun CanvasActivity.extendedOnPixelTapped(instance: MyCanvasView, rectTapped: RectF) {
    val defaultErasePaint = Paint().apply {
        style = Paint.Style.FILL
        color = currentBackground ?: Color.WHITE
    }

    val defaultRectPaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = false
        color = getSelectedColor()
    }

    val rectangleData = instance.rectangles.keys.toList()

    when (currentTool) {
        Tools.PENCIL_TOOL -> {
            instance.rectangles[rectTapped] = defaultRectPaint
            instance.extraCanvas.apply {
                drawRect(rectTapped, defaultRectPaint)
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