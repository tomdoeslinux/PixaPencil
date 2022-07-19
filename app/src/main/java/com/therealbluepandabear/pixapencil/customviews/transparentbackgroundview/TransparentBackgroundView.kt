package com.therealbluepandabear.pixapencil.customviews.transparentbackgroundview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.extensions.drawTransparent
import com.therealbluepandabear.pixapencil.utility.compat.PaintCompat
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants


/** This view is crucial in giving the user the indication that the bitmap is transparent -- not white.
 * We do this by simply drawing a checkerboard pattern */
class TransparentBackgroundView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
    private lateinit var transparentBackgroundViewCanvas: Canvas
    lateinit var transparentBackgroundViewBitmap: Bitmap

    private var bitmapWidth: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    private var bitmapHeight: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT

    private var viewWidth: Int = bitmapWidth        // this will change at runtime
    private var viewHeight: Int = bitmapHeight      // this will change at runtime

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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::transparentBackgroundViewBitmap.isInitialized) {
            transparentBackgroundViewBitmap.recycle()
        }

        transparentBackgroundViewBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        transparentBackgroundViewCanvas = Canvas(transparentBackgroundViewBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        if (::transparentBackgroundViewBitmap.isInitialized) {
            val (matrix, _, _) = transparentBackgroundViewBitmap.calculateMatrix(viewWidth.toFloat(), viewHeight.toFloat())
            canvas.drawBitmap(transparentBackgroundViewBitmap, matrix, PaintCompat.getSDK28PaintOrNull())

            transparentBackgroundViewBitmap.drawTransparent()
        }
    }
}