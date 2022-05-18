package com.therealbluepandabear.pixapencil.customviews.transparentbackgroundview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import com.therealbluepandabear.pixapencil.activities.canvas.index
import com.therealbluepandabear.pixapencil.customviews.interface_.PixelatedView
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.ScaleFactorWHInfo
import com.therealbluepandabear.pixapencil.utility.PaintCompatUtilities
import com.therealbluepandabear.pixapencil.utility.ScaleFactorWHCalculator
import com.therealbluepandabear.pixapencil.utility.StringConstants

@SuppressLint("ViewConstructor")
class TransparentBackgroundView(context: Context, override var canvasWidth: Int, override var canvasHeight: Int) : View(context), PixelatedView {
    private lateinit var transparentBackgroundViewCanvas: Canvas
    lateinit var transparentBackgroundViewBitmap: Bitmap

    override var scaleWidth = 0f
    override var scaleHeight = 0f

    var color = Color.parseColor(StringConstants.Colors.PixelGridViewCheckerboardColor)

    override var currentIndex = index!!

    override var st = false

    override var dimenCW = 0
    override var dimenCH = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (dimenCW != 0 && dimenCH != 0) {
            setMeasuredDimension(
                dimenCW,
                dimenCH
            )
        } else {
            if (index != -1) {
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

        if (index == -1) {
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
            val scaleFactorWHInfo: ScaleFactorWHInfo = ScaleFactorWHCalculator.calculate(canvasWidth, canvasHeight, resources)

            val scaleFactorW = scaleFactorWHInfo.scaleFactorW
            val scaleFactorH = scaleFactorWHInfo.scaleFactorH

            val calculatedMatrixInfo = transparentBackgroundViewBitmap.calculateMatrix(
                scaleFactorW.toFloat(),
                scaleFactorH.toFloat()
            )

            val calculatedMatrix = calculatedMatrixInfo.matrix

            this.scaleWidth = calculatedMatrixInfo.scaleWidth
            this.scaleHeight = calculatedMatrixInfo.scaleHeight

            canvas.drawBitmap(
                transparentBackgroundViewBitmap,
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