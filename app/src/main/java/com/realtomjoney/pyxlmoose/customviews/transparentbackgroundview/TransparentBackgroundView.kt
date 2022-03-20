package com.realtomjoney.pyxlmoose.customviews.transparentbackgroundview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.view.View
import com.realtomjoney.pyxlmoose.activities.canvas.binding
import com.realtomjoney.pyxlmoose.activities.canvas.currentPixelArtObj
import com.realtomjoney.pyxlmoose.activities.canvas.index
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringConstants

@SuppressLint("ViewConstructor")
class TransparentBackgroundView(context: Context, private var canvasWidth: Int, private var canvasHeight: Int) : View(context) {
    private lateinit var transparentBackgroundViewCanvas: Canvas
    lateinit var transparentBackgroundViewBitmap: Bitmap

    var scaleWidth = 0f
    var scaleHeight = 0f

    var color = Color.parseColor(StringConstants.TBV_C1)

    var currentIndex = index!!

    var st = false

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

    var dimenCW = 0
    var dimenCH = 0

    override fun onDraw(canvas: Canvas) {
        if (::transparentBackgroundViewBitmap.isInitialized) {
            var scaleFactorW = 0
            var scaleFactorH = 0

            // Warning: slight modifications may have a flow-on effect throughout the rest of the code, I recommend you keep this part how it is

            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (canvasWidth == canvasHeight) {
                    scaleFactorW = binding.activityCanvasRootLayout.measuredHeight
                    scaleFactorH = binding.activityCanvasRootLayout.measuredHeight
                } else if (canvasWidth > canvasHeight) {
                    scaleFactorW = binding.activityCanvasRootLayout.measuredHeight

                    val ratio = canvasHeight.toDouble() / canvasWidth.toDouble()

                    scaleFactorH = (scaleFactorW * ratio).toInt()
                } else {
                    scaleFactorH = binding.activityCanvasRootLayout.measuredHeight

                    val ratio = canvasWidth.toDouble() / canvasHeight.toDouble()

                    scaleFactorW = (scaleFactorH * ratio).toInt()
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (canvasWidth == canvasHeight) {
                    scaleFactorW = resources.displayMetrics.widthPixels
                    scaleFactorH = resources.displayMetrics.widthPixels
                } else if (canvasWidth > canvasHeight) {
                    scaleFactorW = binding.activityCanvasRootLayout.measuredWidth

                    val ratio = canvasHeight.toDouble() / canvasWidth.toDouble()

                    scaleFactorH = (scaleFactorW * ratio).toInt()
                } else {
                    scaleFactorH = binding.activityCanvasRootLayout.measuredWidth

                    val ratio = canvasWidth.toDouble() / canvasHeight.toDouble()

                    scaleFactorW = (scaleFactorH * ratio).toInt()
                }
            }

            when {
                canvasWidth == canvasHeight -> {
                    canvas.drawBitmap(
                        transparentBackgroundViewBitmap,
                        calculateMatrix(transparentBackgroundViewBitmap,
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
                        transparentBackgroundViewBitmap,
                        calculateMatrix(transparentBackgroundViewBitmap,
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
                        transparentBackgroundViewBitmap,
                        calculateMatrix(transparentBackgroundViewBitmap,
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

            for (i_1 in 0 until canvasWidth) {
                for (i_2 in 0 until canvasHeight) {
                    if (i_1 % 2 == 0) {
                        if (i_1 % 2 == 0
                            &&
                            i_2 % 2 == 0) {
                            transparentBackgroundViewBitmap.setPixel(i_1, i_2, color)
                        } else {
                            transparentBackgroundViewBitmap.setPixel(i_1, i_2, Color.WHITE)
                        }
                    } else {
                        if (i_1 % 2 != 0
                            &&
                            i_2 % 2 != 0) {
                            transparentBackgroundViewBitmap.setPixel(i_1, i_2, color)
                        } else {
                            transparentBackgroundViewBitmap.setPixel(i_1, i_2, Color.WHITE)
                        }
                    }
                }
            }
        }
    }
}