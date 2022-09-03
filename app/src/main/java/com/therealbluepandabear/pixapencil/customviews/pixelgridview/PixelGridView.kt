package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.BitmapResolution
import com.therealbluepandabear.pixapencil.enums.FlipValue
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.extensions.createMutableClone
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.compat.PaintCompat
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

class PixelGridView(context: Context, attributeSet: AttributeSet): View(context, attributeSet){
    lateinit var pixelGridViewBitmap: Bitmap

    private var bitmapWidth: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    private var bitmapHeight: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT

    private var viewWidth: Int = bitmapWidth        // this will change at runtime
    private var viewHeight: Int = bitmapHeight      // this will change at runtime

    private var existingBitmap: Bitmap? = null

    var scaleWidth = 0f
    var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    lateinit var caller: CanvasFragmentListener

    var pixelPerfectMode: Boolean = false
    var gridEnabled = false
    var xm = 0f

    var path1 = Path()
    var path2 = Path()

    var shadingMode: Boolean = false
    val shadingMap = mutableListOf<Coordinates>()

    fun setBitmapWidth(bitmapWidth: Int) {
        this.bitmapWidth = bitmapWidth
        invalidate()
        requestLayout()
    }

    fun setBitmapHeight(bitmapHeight: Int) {
        this.bitmapHeight = bitmapHeight
        invalidate()
        requestLayout()
    }

    fun setViewWidth(viewWidth: Int) {
        this.viewWidth = viewWidth
        invalidate()
        requestLayout()
    }

    fun setViewHeight(viewHeight: Int) {
        this.viewHeight = viewHeight
        invalidate()
        requestLayout()
    }

    fun setExistingBitmap(existingBitmap: Bitmap) {
        this.existingBitmap = existingBitmap
    }

    var gridPaint = Paint().apply {
        strokeWidth = 1f
        pathEffect = null
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isDither = true
        isAntiAlias = true
        isFilterBitmap = false
    }

    private fun drawGrid(canvas: Canvas) {
        extendedDrawGrid(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        caller = context as CanvasFragmentListener

        if (::pixelGridViewBitmap.isInitialized) {
            pixelGridViewBitmap.recycle()
        }

        pixelGridViewBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)

        if (existingBitmap != null) {
            pixelGridViewBitmap = existingBitmap!!.createMutableClone()
        }

        caller.onViewLoaded()
    }

    @SuppressLint("ClickableViewAccessibility")
    /** I will un-suppress in future commits **/
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return extendedOnTouchEvent(event)
    }

    fun saveAsImage(
        format: BitmapCompressFormat,
        resolution: BitmapResolution,
        coordinatorLayout: CoordinatorLayout,
        projectTitle: String,
        flipMatrix: List<FlipValue>,
        compressionOutputQuality: Int = IntConstants.COMPRESSION_QUALITY_MAX,
        onTaskFinished: (OutputCode) -> Unit) {
        extendedSaveAsImage(
            format,
            resolution,
            coordinatorLayout,
            projectTitle,
            flipMatrix,
            compressionOutputQuality,
            onTaskFinished)
    }

    /** Use this code only in onMeasure **/

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            val (matrix, sw, sh) = pixelGridViewBitmap.calculateMatrix(viewWidth.toFloat(), viewHeight.toFloat())
            canvas.drawBitmap(pixelGridViewBitmap, matrix, PaintCompat.getSDK28PaintOrNull())
            scaleWidth = sw
            scaleHeight = sh

            if (gridEnabled) {
                drawGrid(canvas)
            }
        }
    }
}
