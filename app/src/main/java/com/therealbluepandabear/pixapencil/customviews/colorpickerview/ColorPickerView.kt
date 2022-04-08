package com.therealbluepandabear.pixapencil.customviews.colorpickerview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View

@SuppressLint("DrawAllocation")

class ColorPickerView (context: Context) : View(context) {
    private lateinit var colorPickerViewCanvas: Canvas
    private lateinit var colorPickerViewBitmap: Bitmap

    private val arr = FloatArray(3)

    private var hue = 0f
    private var saturation = 0f
    private var value = 1f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::colorPickerViewBitmap.isInitialized) {
            colorPickerViewBitmap.recycle()
        }

        colorPickerViewBitmap = Bitmap.createBitmap(360, 200, Bitmap.Config.ARGB_8888)
        colorPickerViewCanvas = Canvas(colorPickerViewBitmap)

        arr[0] = hue
        arr[1] = saturation
        arr[2] = value

        val inc = 0.01f

        for (i_1 in 0 until colorPickerViewBitmap.width) {
            hue++

            for (i_2 in 0 until colorPickerViewBitmap.height) {
                val color: Int = if (i_2 == 0) {
                    Color.WHITE
                } else {
                    Color.HSVToColor(arr)
                }

                colorPickerViewBitmap.setPixel(i_1, i_2, color)

                if (saturation >= 1) {
                    value -= inc
                }

                if (saturation < 1) {
                    saturation += inc
                }

                arr[0] = hue
                arr[1] = saturation
                arr[2] = value
            }

            saturation = 0f
            value = 1f
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (::colorPickerViewBitmap.isInitialized) {
            canvas.drawBitmap(Bitmap.createScaledBitmap(colorPickerViewBitmap, this.width, 825, false)!!, 0f, 0f, null)
            invalidate()
        }
    }
}