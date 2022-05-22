package com.therealbluepandabear.pixapencil.customviews.transparentbackgroundview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.therealbluepandabear.pixapencil.customviews.interface_.PixelatedView
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.PaintCompatUtilities
import com.therealbluepandabear.pixapencil.utility.ScaleFactorWHCalculator
import com.therealbluepandabear.pixapencil.utility.StringConstants

class TransparentBackgroundView : View, PixelatedView {
    private lateinit var transparentBackgroundViewCanvas: Canvas
    lateinit var transparentBackgroundViewBitmap: Bitmap

    override var scaleWidth = 0f
    override var scaleHeight = 0f

    var color = Color.parseColor(StringConstants.Colors.PixelGridViewCheckerboardColor)

    override var currentIndex: Int = -1

    override var st = false

    override var dimenCW = 0
    override var dimenCH = 0

    override var canvasWidth: Int = IntConstants.DefaultCanvasWidthHeight
    override var canvasHeight: Int = IntConstants.DefaultCanvasWidthHeight

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(
        context: Context,
        canvasWidth: Int,
        canvasHeight: Int,
        currentIndex: Int
    ) : super(context) {
        this.canvasWidth = canvasWidth
        this.canvasHeight = canvasHeight
        this.currentIndex = currentIndex
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

                invalidate()
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

        if (::transparentBackgroundViewBitmap.isInitialized) {
            transparentBackgroundViewBitmap.recycle()
        }

        if (currentIndex == -1) {
            transparentBackgroundViewBitmap =
                Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            transparentBackgroundViewCanvas = Canvas(transparentBackgroundViewBitmap)
        } else {
            val currentBitmap = getCurrentBitmap(this.context)

            canvasWidth = currentBitmap.width
            canvasHeight = currentBitmap.height

            transparentBackgroundViewBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            transparentBackgroundViewCanvas = Canvas(transparentBackgroundViewBitmap)

            transparentBackgroundViewCanvas.drawBitmap(currentBitmap, 0f, 0f, PaintCompatUtilities.getSDK28PaintOrNull())

            postInvalidate()
        }

    }

    override fun onDraw(canvas: Canvas) {
        if (::transparentBackgroundViewBitmap.isInitialized) {
            val (scaleFactorW, scaleFactorH) = ScaleFactorWHCalculator.calculate(canvasWidth, canvasHeight, resources.configuration.orientation, resources)

            val (matrix, scaleWidth, scaleHeight) = transparentBackgroundViewBitmap.calculateMatrix(scaleFactorW.toFloat(), scaleFactorH.toFloat())

            this.scaleWidth = scaleWidth
            this.scaleHeight = scaleHeight

            canvas.drawBitmap(transparentBackgroundViewBitmap, matrix, PaintCompatUtilities.getSDK28PaintOrNull())

            dimenCW = scaleFactorW
            dimenCH = scaleFactorH

            if (!st) {
                requestLayout()
                postInvalidate()
                invalidate()
                st = true
            }

            for (i_1 in 0 until canvasWidth) {
                for (i_2 in 0 until canvasHeight) {
                    val coordinates = Coordinates.staticSet(i_1, i_2)

                    if (i_1 % 2 == 0) {
                        if (i_1 % 2 == 0
                            &&
                            i_2 % 2 == 0) {
                            transparentBackgroundViewBitmap.setPixel(coordinates, color)
                        } else {
                            transparentBackgroundViewBitmap.setPixel(coordinates, Color.WHITE)
                        }
                    } else {
                        if (i_1 % 2 != 0
                            &&
                            i_2 % 2 != 0) {
                            transparentBackgroundViewBitmap.setPixel(coordinates, color)
                        } else {
                            transparentBackgroundViewBitmap.setPixel(coordinates, Color.WHITE)
                        }
                    }
                }
            }
        }
    }
}