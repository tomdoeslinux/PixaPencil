package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.BitmapAction

class AlgorithmInfoParameter {
    lateinit var bitmap: Bitmap
    lateinit var currentBitmapAction: BitmapAction
    var color: Int = Color.BLACK

    companion object {
        fun pass(_bitmap: Bitmap, _currentBitmapAction: BitmapAction, _color: Int): AlgorithmInfoParameter {
            val toReturn = AlgorithmInfoParameter().apply {
                bitmap = _bitmap
                currentBitmapAction = _currentBitmapAction
                color = _color
            }

            return toReturn
        }
    }
}
