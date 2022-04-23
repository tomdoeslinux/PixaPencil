package com.therealbluepandabear.pixapencil.customviews.transparentbackgroundview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.currentPixelArtObj
import com.therealbluepandabear.pixapencil.activities.canvas.index
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.calculateMatrix
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.StringConstants

@SuppressLint("ViewConstructor")
class TransparentBackgroundView(context: Context, private var canvasWidth: Int, private var canvasHeight: Int) : View(context) {
    private lateinit var transparentBackgroundViewCanvas: Canvas
    lateinit var transparentBackgroundViewBitmap: Bitmap

    private var scaleWidth = 0f
    private var scaleHeight = 0f

    var color = Color.parseColor(StringConstants.Colors.PixelGridViewCheckerboardColor)

    private var currentIndex = index!!

    private var st = false

    private var dimenCW = 0
    private var dimenCH = 0

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
            val currentBitmap = getCurrentBitmap()

            canvasWidth = currentBitmap.width
            canvasHeight = currentBitmap.height

            transparentBackgroundViewBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            transparentBackgroundViewCanvas = Canvas(transparentBackgroundViewBitmap)

            transparentBackgroundViewCanvas.drawBitmap(currentBitmap, 0f, 0f, null)

            postInvalidate()
        }

    }

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
        if (::transparentBackgroundViewBitmap.isInitialized) {
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

            val calculatedMatrixInfo = transparentBackgroundViewBitmap.calculateMatrix(
                scaleFactorW.toFloat(),
                scaleFactorH.toFloat()
            )

            val calculatedMatrix = calculatedMatrixInfo.matrix

            this.scaleWidth = calculatedMatrixInfo.scaleWidth
            this.scaleHeight = calculatedMatrixInfo.scaleHeight

            when {
                canvasWidth == canvasHeight -> {
                    canvas.drawBitmap(
                        transparentBackgroundViewBitmap,
                        calculatedMatrix,
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
                        transparentBackgroundViewBitmap,
                        calculatedMatrix,
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
                        transparentBackgroundViewBitmap,
                        calculatedMatrix,
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