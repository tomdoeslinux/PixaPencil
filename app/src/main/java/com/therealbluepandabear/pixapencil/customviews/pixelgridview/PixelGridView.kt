package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.currentPixelArtObj
import com.therealbluepandabear.pixapencil.activities.canvas.index
import com.therealbluepandabear.pixapencil.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener
import com.therealbluepandabear.pixapencil.models.*
import com.therealbluepandabear.pixapencil.utility.PaintCompatUtilities
import com.therealbluepandabear.pixapencil.utility.ScaleFactorWHCalculator

@SuppressLint("ViewConstructor")
class PixelGridView(context: Context, var canvasWidth: Int, var canvasHeight: Int) : View(context) {
    lateinit var pixelGridViewCanvas: Canvas
    lateinit var pixelGridViewBitmap: Bitmap

    var scaleWidth = 0f
    var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    var bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var undoStack: MutableList<BitmapAction> = mutableListOf()

    var currentBitmapAction: BitmapAction? = null

    var currentBrush: Brush? = null

    var pixelPerfectMode: Boolean = false

    var gridEnabled = false

    private var currentIndex = index!!

    lateinit var caller: CanvasFragmentListener

    private var path1 = Path()
    private var path2 = Path()

    private var xm = 0f

    var dimenCW = 0
    var dimenCH = 0

    private val gridPaint = Paint().apply {
        strokeWidth = 1f
        pathEffect = null
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isDither = true
        isAntiAlias = true
        isFilterBitmap = false
    }

    private var st = false

    var symmetryMode: SymmetryMode = SymmetryMode.defaultSymmetryMode

    var shadingMode: Boolean = false

    val shadingMap = mutableListOf<Coordinates>()

    private fun drawGrid(canvas: Canvas) {
        gridPaint.isAntiAlias = outerCanvasInstance.cardViewParent.scaleX <= 3
        gridPaint.alpha = outerCanvasInstance.cardViewParent.scaleX.toInt() * 100

        xm = 0f
        path1.reset()
        path2.reset()

        val dvr = if (canvasWidth >= canvasHeight) {
            canvasWidth
        } else {
            canvasHeight
        }

        for (i in 0 until dvr) {
            if (canvasWidth >= canvasHeight) {
                path1.lineTo(xm, width.toFloat())
                path2.lineTo(width.toFloat(), xm)
            } else {
                path1.lineTo(xm, height.toFloat())
                path2.lineTo(width.toFloat(), xm)
            }

            xm += scaleWidth
            path1.moveTo(xm, 0f)
            path2.moveTo(0f, xm)
        }

        canvas.drawPath(path1, gridPaint)
        canvas.drawPath(path2, gridPaint)
    }


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

            pixelGridViewCanvas.drawBitmap(currentBitmap, 0f, 0f, PaintCompatUtilities.getPreSDK28PaintOrNull())

            outerCanvasInstance.rotate(getCurrentPixelArtObj().rotation.toInt(), false)

            postInvalidate()
        }

        caller.onViewLoaded()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return extendedDispatchTouchEvent(event)
    }

    fun undo() {
        extendedUndo()
    }

    fun redo() {
        extendedRedo()
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

    fun overrideSetPixel(coordinates: Coordinates, color: Int, ignoreBrush: Boolean = false, saveToBitmapAction: Boolean = true, ignoreSymmetry: Boolean = false) {
        extendedOverrideSetPixel(coordinates, color, ignoreBrush, saveToBitmapAction, ignoreSymmetry)
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
        throw IllegalArgumentException(this.context.getString(R.string.exception_accessing_negative_index_message_in_code_str))
    }

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            val scaleFactorWHInfo: ScaleFactorWHInfo = ScaleFactorWHCalculator.calculate(canvasWidth, canvasHeight, resources)

            val scaleFactorW = scaleFactorWHInfo.scaleFactorW
            val scaleFactorH = scaleFactorWHInfo.scaleFactorH

            val calculatedMatrixInfo = pixelGridViewBitmap.calculateMatrix(
                scaleFactorW.toFloat(),
                scaleFactorH.toFloat()
            )

            val calculatedMatrix = calculatedMatrixInfo.matrix

            this.scaleWidth = calculatedMatrixInfo.scaleWidth
            this.scaleHeight = calculatedMatrixInfo.scaleHeight

            canvas.drawBitmap(
                pixelGridViewBitmap,
                calculatedMatrix,
                PaintCompatUtilities.getPreSDK28PaintOrNull())

            dimenCW = scaleFactorW
            dimenCH = scaleFactorH

            if (!st) {
                requestLayout()
                postInvalidate()
                invalidate()
                st = true
            }

            if (gridEnabled) {
                drawGrid(canvas)
            }
        }
    }
}
