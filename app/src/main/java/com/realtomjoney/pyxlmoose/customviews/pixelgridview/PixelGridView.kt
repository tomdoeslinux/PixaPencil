package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.Brush
import com.realtomjoney.pyxlmoose.models.Coordinates

@SuppressLint("ViewConstructor")
class PixelGridView (context: Context, var canvasSize: Int, private var isEmpty: Boolean) : View(context){
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
            pixelGridViewBitmap = Bitmap.createBitmap(canvasSize, canvasSize, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
        }

        applyPixelPerfectValueFromPreference()
    }

    override fun dispatchTouchEvent(event: MotionEvent) = extendedDispatchTouchEvent(event)

    fun undo() = extendedUndo()

    fun clearCanvas() = extendedClearCanvas()

    fun getNumberOfUniqueColors() = extendedGetNumberOfUniqueColors()

    fun replacePixelsByColor(colorToFind: Int, colorToReplace: Int) = extendedReplacePixelsByColor(colorToFind, colorToReplace)

    fun applyBitmapFilter(lambda: (Int) -> Int) = extendedApplyBitmapFilter(lambda)

    fun overrideSetPixel(x: Int, y: Int, color: Int, ignoreBrush: Boolean = false) =
        extendedOverrideSetPixel(x, y, color, ignoreBrush)

    fun replaceBitmap(newBitmap: Bitmap) = extendedReplaceBitmap(newBitmap)

    fun saveAsImage(format: Bitmap.CompressFormat) = extendedSaveAsImage(format)

    private fun applyPixelPerfectValueFromPreference() = extendedApplyPixelPerfectValueFromPreference()

    private fun calculateMatrix(bm: Bitmap, newHeight: Int, newWidth: Int) = extendedCalculateMatrix(bm, newHeight, newWidth)

    fun coordinatesInCanvasBounds(coordinates: Coordinates) = (coordinates.x in 0 until canvasSize && coordinates.y in 0 until canvasSize)

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            canvas.drawBitmap(pixelGridViewBitmap, calculateMatrix(pixelGridViewBitmap, this.width, this.width), null)
        }
    }
}
