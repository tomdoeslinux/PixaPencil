package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import android.graphics.*
import com.realtomjoney.pyxlmoose.activities.canvas.canvasInstance
import android.graphics.Bitmap
import com.realtomjoney.pyxlmoose.models.*

@SuppressLint("ViewConstructor")
class PixelGridView (context: Context, var spanCount: Int, private var isEmpty: Boolean) : View(context) {
    lateinit var pixelGridViewCanvas: Canvas
    lateinit var pixelGridViewBitmap: Bitmap

    var scaleWidth = 0f
    var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    val bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var currentBitmapAction: BitmapAction? = null

    var currentBrush: Brush? = null

    var pixelPerfectMode: Boolean = false

    lateinit var caller: CanvasFragmentListener

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        caller = context as CanvasFragmentListener

        if (::pixelGridViewBitmap.isInitialized) {
            pixelGridViewBitmap.recycle()
        }

        if (!isEmpty) {
            pixelGridViewBitmap = Bitmap.createBitmap(spanCount, spanCount, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent) = extendedDispatchTouchEvent(event)

    fun undo() = extendedUndo()

    fun redo() {
        // TODO - add redo functionality asap
    }

    fun clearCanvas() = extendedClearCanvas()

    fun getNumberOfUniqueColors() = extendedGetNumberOfUniqueColors()

    fun replacePixelsByColor(colorToFind: Int, colorToReplace: Int) = extendedReplacePixelsByColor(colorToFind, colorToReplace)

    fun applyBitmapFilter(lambda: (Int) -> Int) = extendedApplyBitmapFilter(lambda)

    fun overrideSetPixel(x: Int, y: Int, color: Int) = extendedOverrideSetPixel(x, y, color)

    fun replaceBitmap(newBitmap: Bitmap) = extendedReplaceBitmap(newBitmap)

    private fun getMatrix(bm: Bitmap, newHeight: Int, newWidth: Int): Matrix {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        this.scaleWidth = scaleWidth
        this.scaleHeight = scaleHeight

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        return matrix
    }
    
    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            canvas.drawBitmap(pixelGridViewBitmap, getMatrix(pixelGridViewBitmap, this.width, this.width), null)
        }
    }

}