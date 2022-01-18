package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import android.graphics.*
import com.realtomjoney.pyxlmoose.activities.canvas.canvasInstance
import android.graphics.Bitmap
import com.realtomjoney.pyxlmoose.models.*


class PixelGridView (context: Context, private var spanCount: Int, private var isEmpty: Boolean) : View(context) {
    lateinit var pixelGridViewCanvas: Canvas
    lateinit var pixelGridViewBitmap: Bitmap

    var scaleWidth = 0f
    var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    val bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var currentBitmapAction: BitmapAction? = null

    var currentBrush: Brush? = null

    private lateinit var caller: CanvasFragmentListener

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        caller = context as CanvasFragmentListener

        if (::pixelGridViewBitmap.isInitialized) {
            pixelGridViewBitmap.recycle()
        }

        if (!isEmpty) {
            pixelGridViewBitmap = Bitmap.createBitmap(spanCount, spanCount, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val coordinateX = (event.x / scaleWidth).toInt()
        val coordinateY = (event.y / scaleWidth).toInt()

        if (currentBitmapAction == null) {
            currentBitmapAction = BitmapAction(mutableListOf())
        }

        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                if (coordinateX in 0 until spanCount && coordinateY in 0 until spanCount) {
                    caller.onPixelTapped(Coordinates(coordinateX, coordinateY))
                } else {
                    prevX = null
                    prevY = null
                }
            }
            MotionEvent.ACTION_DOWN -> {
                if (coordinateX in 0 until spanCount && coordinateY in 0 until spanCount) {
                    caller.onPixelTapped(Coordinates(coordinateX, coordinateY))
                } else {
                    prevX = null
                    prevY = null
                }
            }
            MotionEvent.ACTION_UP -> {
                caller.onActionUp()
            }
        }

        invalidate()

        return true
    }

    fun undo() = extendedUndo()

    fun redo() {
        // TODO - add in next commit
    }

    fun clearCanvas() = extendedClearCanvas()

    fun getNumberOfUniqueColors() = extendedGetNumberOfUniqueColors()

    fun replacePixelsByColor(colorToFind: Int, colorToReplace: Int) {
        currentBitmapAction = BitmapAction(mutableListOf(), true)

        for (i_1 in 0 until pixelGridViewBitmap.width) {
            for (i_2 in 0 until pixelGridViewBitmap.height) {
                currentBitmapAction!!.actionData.add(BitmapActionData(
                    Coordinates(i_1, i_2),
                    pixelGridViewBitmap.getPixel(i_1, i_2),
                ))

                if (pixelGridViewBitmap.getPixel(i_1, i_2) == colorToFind) {
                    pixelGridViewBitmap.setPixel(i_1, i_2, colorToReplace)
                }
            }
        }
        invalidate()
    }

    fun applyBitmapFilter(lambda: (Int) -> Int) {
        currentBitmapAction = BitmapAction(mutableListOf(), true)

        for (i_1 in 0 until pixelGridViewBitmap.width) {
            for (i_2 in 0 until pixelGridViewBitmap.height) {
                if (pixelGridViewBitmap.getPixel(i_1, i_2) != Color.TRANSPARENT) {
                    val color = lambda(pixelGridViewBitmap.getPixel(i_1, i_2))

                    currentBitmapAction!!.actionData.add(BitmapActionData(
                        Coordinates(i_1, i_2),
                        pixelGridViewBitmap.getPixel(i_1, i_2),
                    ))

                    pixelGridViewBitmap.setPixel(i_1, i_2, color)
                }
            }
        }

        canvasInstance.myCanvasViewInstance.bitmapActionData.add(canvasInstance.myCanvasViewInstance.currentBitmapAction!!)
        canvasInstance.myCanvasViewInstance.currentBitmapAction = null

        invalidate()
    }

    fun overrideSetPixel(x: Int, y: Int, color: Int) {
        val xyPosition = Coordinates(x, y)
        if (currentBrush == null) {
            pixelGridViewBitmap.setPixel(xyPosition.x, xyPosition.y, color)
        } else {
            pixelGridViewBitmap.setPixel(xyPosition.x, xyPosition.y, color)
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(xyPosition)) {
                if (xyPosition_2.x in 0 until spanCount && xyPosition_2.y in 0 until spanCount) {
                    canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(
                        xyPosition_2,
                        pixelGridViewBitmap.getPixel(xyPosition_2.x, xyPosition_2.y)
                    ))
                    pixelGridViewBitmap.setPixel(xyPosition_2.x, xyPosition_2.y, color)
                }
            }
        }
    }

    fun replaceBitmap(newBitmap: Bitmap) {
        pixelGridViewBitmap = Bitmap.createBitmap(newBitmap.width, newBitmap.height, Bitmap.Config.ARGB_8888)
        pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
        spanCount = newBitmap.width
        pixelGridViewCanvas.drawBitmap(newBitmap, 0f, 0f, null)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) canvas.drawBitmap(getResizedBitmap(pixelGridViewBitmap, this.width, this.width)!!, 0f, 0f, null)
    }

}