package com.therealbluepandabear.pixapencil.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.BitmapAction

class AlgorithmInfoParameter {
    lateinit var canvasCommandsHelperInstance: CanvasActivity.CanvasCommandsHelper
    lateinit var bitmap: Bitmap
    lateinit var currentBitmapAction: BitmapAction
    var color: Int = Color.BLACK

    companion object {
        fun create(_canvasCommandsHelperInstance: CanvasActivity.CanvasCommandsHelper, _bitmap: Bitmap, _currentBitmapAction: BitmapAction, _color: Int): AlgorithmInfoParameter {
            val toReturn = AlgorithmInfoParameter().apply {
                canvasCommandsHelperInstance = _canvasCommandsHelperInstance
                bitmap = _bitmap
                currentBitmapAction = _currentBitmapAction
                color = _color
            }

            return toReturn
        }
    }
}
