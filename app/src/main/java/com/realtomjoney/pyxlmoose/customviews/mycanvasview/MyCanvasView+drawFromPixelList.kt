package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.realtomjoney.pyxlmoose.models.Pixel
import kotlin.math.sqrt

fun MyCanvasView.extendedDrawFromPixelList(pixelList: List<Pixel>) {
    val pixelListSpanCount = sqrt(pixelList.size.toDouble())
    val scale = thisWidth / pixelListSpanCount

    var pixelIndex = 0

    for (i in 0 until pixelListSpanCount.toInt()) {
        for (i_2 in 0 until pixelListSpanCount.toInt()) {
            val left = (i * scale).toFloat()
            val top = (i_2 * scale).toFloat()

            val right = left + scale.toFloat()
            val bottom = top + scale.toFloat()

            val rect = RectF(left, top, right, bottom)

            val rectPaint = Paint().apply {
                style = Paint.Style.FILL
                isAntiAlias = false
                color = pixelList[pixelIndex].pixelColor ?: Color.WHITE
            }

            rectangles[rect] = rectPaint

            extraCanvas.drawRect(rect, rectPaint)

            pixelIndex++
        }
    }
}
