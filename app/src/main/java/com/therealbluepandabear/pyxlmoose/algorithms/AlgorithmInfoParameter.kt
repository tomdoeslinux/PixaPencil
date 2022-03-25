package com.therealbluepandabear.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.therealbluepandabear.pyxlmoose.customviews.pixelgridview.PixelGridView
import com.therealbluepandabear.pyxlmoose.models.BitmapAction

class AlgorithmInfoParameter {
    lateinit var canvas: PixelGridView
    lateinit var bitmap: Bitmap
    lateinit var currentBitmapAction: BitmapAction
    var color: Int = Color.BLACK

    companion object {
        fun pass(_canvas: PixelGridView, _bitmap: Bitmap, _currentBitmapAction: BitmapAction, _color: Int): AlgorithmInfoParameter {
            val toReturn = AlgorithmInfoParameter().apply {
                canvas = _canvas
                bitmap = _bitmap
                currentBitmapAction = _currentBitmapAction
                color = _color
            }

            return toReturn
        }
    }
}
