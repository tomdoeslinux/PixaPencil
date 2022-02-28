package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
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

    var gridEnabled = false

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

        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun dispatchTouchEvent(event: MotionEvent) = extendedDispatchTouchEvent(event)

    fun showGrid() {
        gridEnabled = true

        invalidate()
    }

    fun hideGrid() {
        gridEnabled = false
        invalidate()
    }

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

    fun coordinatesInCanvasBounds(coordinates: Coordinates) = extendedCoordinatesInCanvasBounds(coordinates)

    private val paint = Paint().apply {
        strokeWidth = 1f
        pathEffect = null
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isDither = true
        isAntiAlias = true
    }

    private var path1 = Path()
    private var path2 = Path()

    private var xm = 0f

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            canvas.drawBitmap(pixelGridViewBitmap, calculateMatrix(pixelGridViewBitmap, this.width, this.width), null)

            if (gridEnabled) {
                paint.isAntiAlias = outerCanvasInstance.cardViewParent.scaleX <= 3
                paint.alpha = outerCanvasInstance.cardViewParent.scaleX.toInt() * 100

                xm = 0f
                path1.reset()
                path2.reset()
                for (i in 0 until canvasSize) {
                    path1.lineTo(xm, height.toFloat())
                    path2.lineTo(height.toFloat(), xm)

                    xm += scaleWidth
                    path1.moveTo(xm, 0f)
                    path2.moveTo(0f, xm)
                }

                canvas.drawPath(path1, paint)
                canvas.drawPath(path2, paint)

            }
        }
    }
}
