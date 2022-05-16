package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.currentPixelArtObj
import com.therealbluepandabear.pixapencil.activities.canvas.index
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.customviews.interface_.CustomView
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener
import com.therealbluepandabear.pixapencil.models.*
import com.therealbluepandabear.pixapencil.utility.PaintCompatUtilities
import com.therealbluepandabear.pixapencil.utility.ScaleFactorWHCalculator

@SuppressLint("ViewConstructor")
class PixelGridView(
    context: Context,
    override var canvasWidth: Int,
    override var canvasHeight: Int,
    val outerCanvasInstance: OuterCanvasFragment,
    val projectTitle: String?) : View(context), CustomView {

    lateinit var pixelGridViewCanvas: Canvas
    lateinit var pixelGridViewBitmap: Bitmap

    override var scaleWidth = 0f
    override var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    var currentBrush: Brush? = null

    var pixelPerfectMode: Boolean = false

    var gridEnabled = false

    override var currentIndex = index!!

    lateinit var caller: CanvasFragmentListener

    var path1 = Path()
    var path2 = Path()

    var xm = 0f

    override var dimenCW = 0
    override var dimenCH = 0

    override var st = false

    val gridPaint = Paint().apply {
        strokeWidth = 1f
        pathEffect = null
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isDither = true
        isAntiAlias = true
        isFilterBitmap = false
    }

    var symmetryMode: SymmetryMode = SymmetryMode.defaultSymmetryMode

    var shadingMode: Boolean = false

    val shadingMap = mutableListOf<Coordinates>()

    private fun drawGrid(canvas: Canvas) {
        extendedDrawGrid(canvas)
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

            pixelGridViewCanvas.drawBitmap(currentBitmap, 0f, 0f, PaintCompatUtilities.getSDK28PaintOrNull())

            outerCanvasInstance.rotate(getCurrentPixelArtObj().rotation.toInt(), false)

            postInvalidate()
        }

        caller.onViewLoaded()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return extendedDispatchTouchEvent(event)
    }

    fun getNumberOfUniqueColors(): List<Int> {
        return extendedGetNumberOfUniqueColors()
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
        return extendedGetCurrentPixelArtObj()
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
                PaintCompatUtilities.getSDK28PaintOrNull())

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
