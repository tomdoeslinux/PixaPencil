package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.realtomjoney.pyxlmoose.activities.canvas.binding
import com.realtomjoney.pyxlmoose.activities.canvas.currentPixelArtObj
import com.realtomjoney.pyxlmoose.activities.canvas.index
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.Brush
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringConstants

@SuppressLint("ViewConstructor")
class PixelGridView(context: Context, var canvasWidth: Int, var canvasHeight: Int) : View(context) {
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

    private var currentIndex = index!!

    lateinit var caller: CanvasFragmentListener

    private var path1 = Path()
    private var path2 = Path()

    private var xm = 0f
    private var ym = 0f

    var dimenCW = 0
    var dimenCH = 0

    private val gridPaint = Paint().apply {
        strokeWidth = 1f
        pathEffect = null
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isDither = true
        isAntiAlias = true
    }

    private var st = false


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (dimenCW != 0 && dimenCH != 0) {
            setMeasuredDimension(
                dimenCW,
                dimenCH
            )
        } else {
            if (currentIndex != -1) {
                val currentPixelArtObj = getCurrentPixelArtObj()

                setMeasuredDimension(
                    currentPixelArtObj.dimenCW,
                    currentPixelArtObj.dimenCH
                )

                postInvalidate()
            } else {
                setMeasuredDimension(
                    widthMeasureSpec,
                    heightMeasureSpec
                )

                postInvalidate()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        caller = context as CanvasFragmentListener

        if (::pixelGridViewBitmap.isInitialized) {
            pixelGridViewBitmap.recycle()
        }

        if (currentIndex == -1) {
            pixelGridViewBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)

            postInvalidate()
        } else {
            val currentBitmap = getCurrentBitmap()

            canvasWidth = currentBitmap.width
            canvasHeight = currentBitmap.height

            pixelGridViewBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)

            pixelGridViewCanvas.drawBitmap(currentBitmap, 0f, 0f, null)

            outerCanvasInstance.rotate(getCurrentPixelArtObj().rotation.toInt(), false)

            postInvalidate()
        }

        applyPixelPerfectValueFromPreference()
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

    fun undo() {
        extendedUndo()
    }

    fun clearCanvas() {
        extendedClearCanvas()
    }

    fun getNumberOfUniqueColors(): List<Int> {
        return extendedGetNumberOfUniqueColors()
    }

    fun replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
        extendedReplacePixelsByColor(colorToFind, colorToReplace)
    }

    fun applyBitmapFilter(lambda: (Int) -> Int) {
        extendedApplyBitmapFilter(lambda)
    }

    fun overrideSetPixel(x: Int, y: Int, color: Int, ignoreBrush: Boolean = false) {
        extendedOverrideSetPixel(x, y, color, ignoreBrush)
    }

    fun replaceBitmap(newBitmap: Bitmap) {
        extendedReplaceBitmap(newBitmap)
    }

    fun saveAsImage(format: Bitmap.CompressFormat) {
        extendedSaveAsImage(format)
    }

    fun coordinatesInCanvasBounds(coordinates: Coordinates): Boolean {
        return extendedCoordinatesInCanvasBounds(coordinates)
    }

    private fun applyPixelPerfectValueFromPreference() {
        extendedApplyPixelPerfectValueFromPreference()
    }

    /** Use this code only in onMeasure **/

    private fun getCurrentPixelArtObj(): PixelArt {
        val pixelArtData = AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreationsNoLiveData()

        return pixelArtData[currentIndex]
    }

    private fun getCurrentBitmap(): Bitmap {
        if (currentIndex != -1) {
            val pixelArtData = AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreationsNoLiveData()

            val gcbCurrentPixelArtObj = pixelArtData[currentIndex]

            currentPixelArtObj = gcbCurrentPixelArtObj

            return BitmapConverter.convertStringToBitmap(currentPixelArtObj.bitmap)!!
        }
        throw IllegalArgumentException(StringConstants.ExceptionAccessingNegativeIndex)
    }

    private fun calculateMatrix(bm: Bitmap, newWidth: Float, newHeight: Float): Matrix {
        val width = bm.width
        val height = bm.height

        val scaleWidth = newWidth / width
        val scaleHeight = newHeight / height

        this.scaleWidth = scaleWidth
        this.scaleHeight = scaleHeight

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        return matrix
    }

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            var scaleFactorW = 0
            var scaleFactorH = 0

            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                when {
                    canvasWidth == canvasHeight -> {
                        scaleFactorW = binding.activityCanvasRootLayout.measuredHeight
                        scaleFactorH = binding.activityCanvasRootLayout.measuredHeight
                    }
                    canvasWidth > canvasHeight -> {
                        scaleFactorW = binding.activityCanvasRootLayout.measuredHeight

                        val ratio = canvasHeight.toDouble() / canvasWidth.toDouble()

                        scaleFactorH = (scaleFactorW * ratio).toInt()
                    }
                    else -> {
                        scaleFactorH = binding.activityCanvasRootLayout.measuredHeight

                        val ratio = canvasWidth.toDouble() / canvasHeight.toDouble()

                        scaleFactorW = (scaleFactorH * ratio).toInt()
                    }
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when {
                    canvasWidth == canvasHeight -> {
                        scaleFactorW = resources.displayMetrics.widthPixels
                        scaleFactorH = resources.displayMetrics.widthPixels
                    }
                    canvasWidth > canvasHeight -> {
                        scaleFactorW = binding.activityCanvasRootLayout.measuredWidth

                        val ratio = canvasHeight.toDouble() / canvasWidth.toDouble()

                        scaleFactorH = (scaleFactorW * ratio).toInt()
                    }
                    else -> {
                        scaleFactorH = binding.activityCanvasRootLayout.measuredWidth

                        val ratio = canvasWidth.toDouble() / canvasHeight.toDouble()

                        scaleFactorW = (scaleFactorH * ratio).toInt()
                    }
                }
            }

            when {
                canvasWidth == canvasHeight -> {
                    canvas.drawBitmap(
                        pixelGridViewBitmap,
                        calculateMatrix(pixelGridViewBitmap,
                            scaleFactorW.toFloat(),
                            scaleFactorH.toFloat()),
                        null)

                    dimenCW = scaleFactorW
                    dimenCH = scaleFactorH

                    if (!st) {
                        requestLayout()
                        postInvalidate()
                        invalidate()
                        st = true
                    }
                }
                canvasWidth > canvasHeight -> {
                    canvas.drawBitmap(
                        pixelGridViewBitmap,
                        calculateMatrix(pixelGridViewBitmap,
                            scaleFactorW.toFloat(),
                            scaleFactorH.toFloat()),
                        null)

                    dimenCW = scaleFactorW
                    dimenCH = scaleFactorH

                    if (!st) {
                        requestLayout()
                        postInvalidate()
                        invalidate()
                        st = true
                    }
                }
                else -> {
                    canvas.drawBitmap(
                        pixelGridViewBitmap,
                        calculateMatrix(pixelGridViewBitmap,
                            scaleFactorW.toFloat(),
                            scaleFactorH.toFloat()),
                        null)

                    dimenCW = scaleFactorW
                    dimenCH = scaleFactorH

                    if (!st) {
                        requestLayout()
                        postInvalidate()
                        invalidate()
                        st = true
                    }
                }
            }
            // TODO - Fix Grid tool bugs

            if (gridEnabled) {
                gridPaint.isAntiAlias = outerCanvasInstance.cardViewParent.scaleX <= 3
                gridPaint.alpha = outerCanvasInstance.cardViewParent.scaleX.toInt() * 100

                xm = 0f
                path1.reset()
                path2.reset()
                for (i in 0 until canvasWidth) {
                    path1.lineTo(xm, height.toFloat())
                    path2.lineTo(height.toFloat(), ym)

                    xm += scaleWidth
                    ym += scaleHeight

                    path1.moveTo(xm, 0f)
                    path2.moveTo(0f, ym)
                }

                canvas.drawPath(path1, gridPaint)
                canvas.drawPath(path2, gridPaint)
            }
        }
    }
}
