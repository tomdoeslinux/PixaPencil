package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.lifecycle.LifecycleOwner
import com.realtomjoney.pyxlmoose.activities.canvas.currentPixelArtObj
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import kotlin.math.sqrt

fun MyCanvasView.extendedLoadData(context: LifecycleOwner, index: Int) {
    AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().observe(context, {
        currentPixelArtObj = it[index]

        val localPixelData = JsonConverter.convertJsonStringToPixelList(currentPixelArtObj.pixelData)
        val spanCount = sqrt(localPixelData.size.toDouble())

        var pixelIndex = 0

        val localPixelDataSpanCount = sqrt(localPixelData.size.toDouble())
        val scale = thisWidth / localPixelDataSpanCount

        for (i in 0 until spanCount.toInt()) {
            for (i_2 in 0 until spanCount.toInt()) {
                val left = (i * scale).toFloat()
                val top = (i_2 * scale).toFloat()

                val right = left + scale.toFloat()
                val bottom = top + scale.toFloat()

                val rect = RectF(left, top, right, bottom)

                val rectPaint = Paint().apply {
                    style = Paint.Style.FILL
                    isAntiAlias = false
                    color = localPixelData[pixelIndex].pixelColor ?: Color.WHITE
                }

                rectangles[rect] = rectPaint

                extraCanvas.drawRect(rect, rectPaint)

                pixelIndex++
            }
        }
    })
}