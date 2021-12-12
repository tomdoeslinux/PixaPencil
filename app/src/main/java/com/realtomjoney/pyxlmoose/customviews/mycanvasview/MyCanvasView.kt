package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.realtomjoney.pyxlmoose.activities.canvas.*
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.models.Pixel

class MyCanvasView (context: Context, var spanCount: Double) : View(context) {
    lateinit var extraCanvas: Canvas
    lateinit var extraBitmap: Bitmap

    val rectangles = mutableMapOf<RectF, Paint?>()

    private lateinit var caller: CanvasFragmentListener

    var thisWidth: Int = 0
    var scale: Double = 0.0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        thisWidth = w

        caller = context as CanvasFragmentListener

        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)

        if (index == -1) {
            scale = (w / spanCount)

            for (i in 0 until spanCount.toInt()) {
                for (i_2 in 0 until spanCount.toInt()) {
                    val left = (i * scale).toFloat()
                    val top = (i_2 * scale).toFloat()

                    val right = left + scale.toFloat()
                    val bottom = top + scale.toFloat()

                    val rect = RectF(left, top, right, bottom)

                    rectangles[rect] = null
                    extraCanvas.drawRect(
                        rect,
                        Paint().apply { style = Paint.Style.FILL; color = Color.WHITE })
                }
            }
        }
    }

    private fun drawRectAt(x: Float, y: Float) {
        for (rect in rectangles.keys) {
            if (rect.contains(x, y)) {
                caller.onPixelTapped(this, rect)
                invalidate()
            }
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> drawRectAt(event.x, event.y)
            MotionEvent.ACTION_DOWN -> drawRectAt(event.x, event.y)
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    fun saveData() = extendedSaveData()

    fun loadData(context: LifecycleOwner, index: Int) = extendedLoadData(context, index)

    fun drawFromPixelList(pixelList: List<Pixel>) = extendedDrawFromPixelList(pixelList)
}