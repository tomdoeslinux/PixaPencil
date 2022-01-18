package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import android.graphics.*
import com.realtomjoney.pyxlmoose.activities.canvas.canvasInstance
import android.graphics.Bitmap
import com.realtomjoney.pyxlmoose.activities.canvas.index
import com.realtomjoney.pyxlmoose.models.*


class PixelGridView (context: Context, private var spanCount: Int) : View(context) {
    lateinit var extraCanvas: Canvas
    lateinit var extraBitmap: Bitmap

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

        if (::extraBitmap.isInitialized) {
            extraBitmap.recycle()
        }

        if (index == -1) {
            extraBitmap = Bitmap.createBitmap(spanCount, spanCount, Bitmap.Config.ARGB_8888)
            extraCanvas = Canvas(extraBitmap)
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
                    caller.onPixelTapped(XYPosition(coordinateX, coordinateY))
                } else {
                    prevX = null
                    prevY = null
                }
            }
            MotionEvent.ACTION_DOWN -> {
                if (coordinateX in 0 until spanCount && coordinateY in 0 until spanCount) {
                    caller.onPixelTapped(XYPosition(coordinateX, coordinateY))
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

        for (i_1 in 0 until extraBitmap.width) {
            for (i_2 in 0 until extraBitmap.height) {
                currentBitmapAction!!.actionData.add(BitmapActionData(
                    XYPosition(i_1, i_2),
                    extraBitmap.getPixel(i_1, i_2),
                ))

                if (extraBitmap.getPixel(i_1, i_2) == colorToFind) {
                    extraBitmap.setPixel(i_1, i_2, colorToReplace)
                }
            }
        }
        invalidate()
    }

    fun applyBitmapFilter(lambda: (Int) -> Int) {
        currentBitmapAction = BitmapAction(mutableListOf(), true)

        for (i_1 in 0 until extraBitmap.width) {
            for (i_2 in 0 until extraBitmap.height) {
                if (extraBitmap.getPixel(i_1, i_2) != Color.TRANSPARENT) {
                    val color = lambda(extraBitmap.getPixel(i_1, i_2))

                    currentBitmapAction!!.actionData.add(BitmapActionData(
                        XYPosition(i_1, i_2),
                        extraBitmap.getPixel(i_1, i_2),
                    ))

                    extraBitmap.setPixel(i_1, i_2, color)
                }
            }
        }

        canvasInstance.myCanvasViewInstance.bitmapActionData.add(canvasInstance.myCanvasViewInstance.currentBitmapAction!!)
        canvasInstance.myCanvasViewInstance.currentBitmapAction = null

        invalidate()
    }

    fun overrideSetPixel(x: Int, y: Int, color: Int) {
        val xyPosition = XYPosition(x, y)
        if (currentBrush == null) {
            extraBitmap.setPixel(xyPosition.x, xyPosition.y, color)
        } else {
            extraBitmap.setPixel(xyPosition.x, xyPosition.y, color)
            for (xyPosition_2 in currentBrush!!.convertBrushInstructionDataToXYPositionData(xyPosition)) {
                if (xyPosition_2.x in 0 until spanCount && xyPosition_2.y in 0 until spanCount) {
                    canvasInstance.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(
                        xyPosition_2,
                        extraBitmap.getPixel(xyPosition_2.x, xyPosition_2.y)
                    ))
                    extraBitmap.setPixel(xyPosition_2.x, xyPosition_2.y, color)
                }
            }
        }
    }

    fun replaceBitmap(newBitmap: Bitmap) {
        extraBitmap = Bitmap.createBitmap(newBitmap.width, newBitmap.height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        spanCount = newBitmap.width
        extraCanvas.drawBitmap(newBitmap, 0f, 0f, null)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (::extraBitmap.isInitialized) canvas.drawBitmap(getResizedBitmap(extraBitmap, this.width, this.width)!!, 0f, 0f, null)
    }

}