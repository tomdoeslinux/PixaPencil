package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
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

@SuppressLint("ViewConstructor")
class PixelGridView(context: Context, var canvasWidth: Int, var canvasHeight: Int, private var isEmpty: Boolean) : View(context) {
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

    var currentIndex = index!!

    lateinit var caller: CanvasFragmentListener

    private var path1 = Path()
    private var path2 = Path()

    private var xm = 0f
    private var ym = 0f

    var dimenCW = 0
    var dimenCH = 0

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
            } else {
                setMeasuredDimension(
                    widthMeasureSpec,
                    heightMeasureSpec
                )
            }
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        caller = context as CanvasFragmentListener

        if (::pixelGridViewBitmap.isInitialized) {
            pixelGridViewBitmap.recycle()
        }

        if (index == -1) {
            pixelGridViewBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
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

    fun undo() = extendedUndo()

    fun clearCanvas() = extendedClearCanvas()

    fun getNumberOfUniqueColors() = extendedGetNumberOfUniqueColors()

    fun replacePixelsByColor(colorToFind: Int, colorToReplace: Int) = extendedReplacePixelsByColor(colorToFind, colorToReplace)

    fun applyBitmapFilter(lambda: (Int) -> Int) = extendedApplyBitmapFilter(lambda)

    fun overrideSetPixel(x: Int, y: Int, color: Int, ignoreBrush: Boolean = false) =
        extendedOverrideSetPixel(x, y, color, ignoreBrush)

    fun replaceBitmap(newBitmap: Bitmap) = extendedReplaceBitmap(newBitmap)

    fun saveAsImage(format: Bitmap.CompressFormat) = extendedSaveAsImage(format)

    fun coordinatesInCanvasBounds(coordinates: Coordinates) = extendedCoordinatesInCanvasBounds(coordinates)

    private fun applyPixelPerfectValueFromPreference() = extendedApplyPixelPerfectValueFromPreference()

    // only use in onMeasure if needed
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
        throw IllegalArgumentException("Cannot access pixel art object with a negative index in list!")
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

    private val paint = Paint().apply {
        strokeWidth = 1f
        pathEffect = null
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isDither = true
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            val widthFloat = width.toFloat()
            val heightFloat = height.toFloat()

            when {
                canvasWidth == canvasHeight -> {
                    canvas.drawBitmap(
                        pixelGridViewBitmap,
                        calculateMatrix(pixelGridViewBitmap,
                            widthFloat,
                            widthFloat),
                        null)

                    dimenCW = (pixelGridViewBitmap.width * scaleWidth).toInt()
                    dimenCH = (pixelGridViewBitmap.height * scaleHeight).toInt()
                }
                canvasWidth > canvasHeight -> {
                    canvas.drawBitmap(
                        pixelGridViewBitmap,
                        calculateMatrix(pixelGridViewBitmap,
                            widthFloat,
                            scaleWidth * canvasHeight),
                        null)

                    dimenCW = (pixelGridViewBitmap.width * scaleWidth).toInt()
                    dimenCH = (scaleWidth * canvasHeight).toInt()
                }
                else -> {
                    canvas.drawBitmap(pixelGridViewBitmap,
                        calculateMatrix(pixelGridViewBitmap,
                            scaleHeight * canvasWidth,
                            heightFloat),
                        null)

                    dimenCW = (scaleHeight * canvasWidth).toInt()
                    dimenCH = (pixelGridViewBitmap.height * scaleHeight).toInt()
                }
            }


            // TODO - Fix Grid tool bugs

            if (gridEnabled) {
                paint.isAntiAlias = outerCanvasInstance.cardViewParent.scaleX <= 3
                paint.alpha = outerCanvasInstance.cardViewParent.scaleX.toInt() * 100

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

                canvas.drawPath(path1, paint)
                canvas.drawPath(path2, paint)
            }
        }
    }

}
