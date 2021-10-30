package com.realtomjoney.pyxlmoose.activity.canvas

import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.PixelArt
import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.StringValues

fun CanvasActivity.extendedOnPause() {
    if (!hasSaved) {
        PixelArtDatabase.addItem(
            PixelArt(
                binding.fragmentHost.drawToBitmap(),
                StringValues.DEFAULT_PROJECT_NAME,
                data,
                false
            )
        )
    }
}