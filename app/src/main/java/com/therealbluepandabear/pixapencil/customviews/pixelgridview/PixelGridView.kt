package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.therealbluepandabear.pixapencil.customviews.interface_.PixelatedView
import com.therealbluepandabear.pixapencil.database.DitherBrushDatabase
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener
import com.therealbluepandabear.pixapencil.models.*
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.compat.PaintCompat
import com.therealbluepandabear.pixapencil.utility.ScaleFactorWHCalculator

class PixelGridView : View, PixelatedView {
    lateinit var pixelGridViewCanvas: Canvas
    lateinit var pixelGridViewBitmap: Bitmap

    override var scaleWidth = 0f
    override var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    var currentBrush: Brush? = null

    var pixelPerfectMode: Boolean = false

    var gridEnabled = false

    override var currentIndex = -1

    lateinit var caller: CanvasFragmentListener

    var path1 = Path()
    var path2 = Path()

    var xm = 0f

    override var dimenCW = 0
    override var dimenCH = 0

    override var st = false

    var gridPaint = Paint().apply {
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

    lateinit var outerCanvasInstance: OuterCanvasFragment

    var projectTitle: String = ""

    override var canvasWidth: Int = IntConstants.DefaultCanvasWidthHeight
    override var canvasHeight: Int = IntConstants.DefaultCanvasWidthHeight

    constructor(
        context: Context,
        canvasWidth: Int,
        canvasHeight: Int,
        outerCanvasInstance: OuterCanvasFragment,
        projectTitle: String?,
        currentIndex: Int
    ) : super(context) {
        this.canvasWidth = canvasWidth
        this.canvasHeight = canvasHeight
        this.outerCanvasInstance = outerCanvasInstance
        if (projectTitle != null) {
            this.projectTitle = projectTitle
        }
        this.currentIndex = currentIndex
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

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
            val currentBitmap = getCurrentBitmap(context)

            canvasWidth = currentBitmap.width
            canvasHeight = currentBitmap.height

            pixelGridViewBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)

            pixelGridViewCanvas.drawBitmap(currentBitmap, 0f, 0f, PaintCompat.getSDK28PaintOrNull())

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

    fun saveAsImage(format: BitmapCompressFormat) {
        extendedSaveAsImage(format)
    }

    /** Use this code only in onMeasure **/

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            val (scaleFactorW, scaleFactorH) = ScaleFactorWHCalculator.calculate(canvasWidth, canvasHeight, resources.configuration.orientation, resources)

            val (matrix, scaleWidth, scaleHeight) = pixelGridViewBitmap.calculateMatrix(scaleFactorW.toFloat(), scaleFactorH.toFloat())

            this.scaleWidth = scaleWidth
            this.scaleHeight = scaleHeight

            canvas.drawBitmap(pixelGridViewBitmap, matrix, PaintCompat.getSDK28PaintOrNull())

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
