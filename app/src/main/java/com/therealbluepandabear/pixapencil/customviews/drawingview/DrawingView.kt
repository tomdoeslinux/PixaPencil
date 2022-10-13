/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.customviews.drawingview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.therealbluepandabear.pixapencil.enums.*
import com.therealbluepandabear.pixapencil.extensions.drawTransparent
import com.therealbluepandabear.pixapencil.extensions.overlay
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.compat.PaintCompat
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var drawingViewBitmap: Bitmap
    private lateinit var boundingRect: RectF

    private lateinit var transparentBackgroundViewBitmap: Bitmap

    private val centerX: Float
        get() = width / 2f
    private val centerY: Float
        get() = height / 2f

    private var scaleWidth = 0f
    private var scaleHeight = 0f

    private var bitmapWidth = IntConstants.DEFAULT_BITMAP_DIMEN
    private var bitmapHeight = IntConstants.DEFAULT_BITMAP_DIMEN

    var pixelPerfectMode: Boolean = false
    var gridEnabled = false
    var shadingMode: Boolean = false

    var prevX: Int? = null
    var prevY: Int? = null

    val shadingMap = mutableListOf<Coordinates>()

    private var onPixelTapped: (Coordinates) -> Unit = { }
    private var onTouchEvent: () -> Unit = { }
    private var onActionUp: () -> Unit = { }

    private var currentZoom = 1f

    private var clipBoundsRect = Rect()
    private var currentRotation: Float = 0f

    private var canvasX = 0f
    private var canvasY = 0f

    private var dx = 0f
    private var dy = 0f

    private val zoomDecimalFormat = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
    }

    var moveMode = false

    companion object {
        const val ZOOM_INCREMENT = 0.2f
    }

    object PaintData {
        val rectPaint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.WHITE
            setShadowLayer(
                10f, 0f, 0f,
                Color.argb(100, 0, 0, 0)
            )
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
    }

    private fun setScaleWH() {
        scaleWidth = boundingRect.width() / drawingViewBitmap.width.toFloat()
        scaleHeight = boundingRect.height() / drawingViewBitmap.height.toFloat()
    }

    private fun setBoundingRect() {
        val rectW =
            if (bitmapWidth > bitmapHeight) width.toFloat()
            else (bitmapWidth.toFloat() / bitmapHeight) * height

        val rectH =
            if (bitmapHeight > bitmapWidth) height.toFloat()
            else (bitmapHeight.toFloat() / bitmapWidth) * width

        val canvasCenter = Point(width / 2, height / 2)

        val left = canvasCenter.x - rectW / 2
        val top = canvasCenter.y - rectH / 2
        val right = canvasCenter.x + rectW / 2
        val bottom = canvasCenter.y + rectH / 2

        boundingRect = RectF(left, top, right, bottom)
    }


    private fun drawGrid(canvas: Canvas) {
        var xm = (boundingRect.top)

        for (i in 0 .. bitmapHeight) {
            canvas.drawLine(
                (boundingRect.left),
                xm,
                (boundingRect.right),
                xm,
                PaintData.gridPaint
            )

            xm += scaleHeight
        }

        var ym = (boundingRect.left)

        for (i in 0 .. bitmapWidth) {
            canvas.drawLine(
                ym,
                (boundingRect.top),
                ym,
                (boundingRect.bottom),
                PaintData.gridPaint
            )

            ym += scaleWidth
        }
    }

    private fun doOnTouchEvent(event: MotionEvent) {
        val x: Float = event.x / currentZoom + clipBoundsRect.left
        val y: Float = event.y / currentZoom + clipBoundsRect.top

        val coordinates = Coordinates(((x - boundingRect.left) / scaleWidth).toInt(), (((y - boundingRect.top) / scaleHeight).toInt()))

        onPixelTapped.invoke(coordinates)
        invalidate()
    }

    private fun drawBitmap(bitmap: Bitmap, canvas: Canvas) {
        canvas.drawBitmap(bitmap, null, boundingRect, PaintCompat.getSDK28PaintOrNull())
    }

    fun isNextZoomOutOfBounds(): Boolean {
        return zoomDecimalFormat.format(currentZoom - (2 * ZOOM_INCREMENT)).toFloat() < 0
    }

    fun zoomIn() {
        currentZoom = zoomDecimalFormat.format(currentZoom + ZOOM_INCREMENT).toFloat()
        invalidate()
    }

    fun zoomOut() {
        if (!isNextZoomOutOfBounds()) {
            currentZoom = zoomDecimalFormat.format(currentZoom - ZOOM_INCREMENT).toFloat()
            invalidate()
        }
    }

    fun resetZoom() {
        currentZoom = 1f
        invalidate()
    }

    fun resetPosition() {
        canvasX = 1f
        canvasY = 1f
        invalidate()
    }

    fun saveAsImage(
        format: BitmapCompressFormat,
        resolution: BitmapResolution,
        coordinatorLayout: CoordinatorLayout,
        projectTitle: String,
        flipMatrix: List<FlipValue>,
        compressionOutputQuality: Int = IntConstants.COMPRESSION_QUALITY_MAX,
        onTaskFinished: (OutputCode) -> Unit) {
        extendedSaveAsImage(format, resolution, coordinatorLayout, projectTitle, flipMatrix, compressionOutputQuality, onTaskFinished)
    }

    fun setBitmapWidth(bitmapWidth: Int) {
        this.bitmapWidth = bitmapWidth
        setBoundingRect()
        requestLayout()
        invalidate()
    }

    fun setBitmapHeight(bitmapHeight: Int) {
        this.bitmapHeight = bitmapHeight
        setBoundingRect()
        requestLayout()
        invalidate()
    }

    fun setOnPixelTapped(onPixelTapped: (Coordinates) -> Unit) {
        this.onPixelTapped = onPixelTapped
    }

    fun setOnTouchEvent(onTouchEvent: () -> Unit) {
        this.onTouchEvent = onTouchEvent
    }

    fun setOnActionUp(onActionUp: () -> Unit) {
        this.onActionUp = onActionUp
    }

    fun rotate(rotationValue: RotationValue) {
        if (!rotationValue.clockwise) {
            currentRotation -= rotationValue.degrees
        } else {
            currentRotation += rotationValue.degrees
        }

        invalidate()
    }

    fun exportBitmap(): Bitmap {
        val dimenW = boundingRect.width().roundToInt()
        val dimenH = boundingRect.height().roundToInt()

        val scaledDrawingViewBitmap = Bitmap.createScaledBitmap(
            drawingViewBitmap,
            dimenW,
            dimenH,
            false
        )

        val scaledTransparentBackgroundViewBitmap = Bitmap.createScaledBitmap(
            transparentBackgroundViewBitmap,
            dimenW,
            dimenH,
            false
        )

        return scaledTransparentBackgroundViewBitmap.overlay(scaledDrawingViewBitmap)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::drawingViewBitmap.isInitialized) {
            drawingViewBitmap.recycle()
        }

        drawingViewBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        transparentBackgroundViewBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.RGB_565)
        transparentBackgroundViewBitmap.drawTransparent()

        setBoundingRect()
        setScaleWH()
        requestLayout()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        onTouchEvent.invoke()

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (moveMode) {
                    dx = canvasX - event.rawX
                    dy = canvasY - event.rawY
                } else {
                    doOnTouchEvent(event)
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (moveMode) {
                    canvasX = event.rawX + dx
                    canvasY = event.rawY + dy
                    invalidate()
                } else {
                    doOnTouchEvent(event)
                }
            }

            MotionEvent.ACTION_UP -> {
                onActionUp.invoke()
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        if (::drawingViewBitmap.isInitialized && ::transparentBackgroundViewBitmap.isInitialized) {
            canvas.save()

            canvas.translate(canvasX, canvasY)
            canvas.scale(currentZoom, currentZoom, centerX, centerY)
            canvas.getClipBounds(clipBoundsRect)
            canvas.drawRect(boundingRect, PaintData.rectPaint)

            drawBitmap(transparentBackgroundViewBitmap, canvas)
            drawBitmap(drawingViewBitmap, canvas)

            if (gridEnabled) {
                drawGrid(canvas)
            }

            canvas.restore()
        }
    }
}