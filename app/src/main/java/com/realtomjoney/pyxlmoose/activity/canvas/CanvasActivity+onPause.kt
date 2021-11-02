@file:Suppress("unused")

package com.realtomjoney.pyxlmoose.activity.canvas

import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.PixelArt
import com.realtomjoney.pyxlmoose.PixelArtDatabase

fun CanvasActivity.extendedOnPause() {
    currentBackground = null
    hasSetBackgroundYet = false
    wantsToChangeBackground = false

    if (binding.titleTextView.text.toString().isBlank()) {
        PixelArtDatabase.addItem(
            PixelArt(
                binding.fragmentHost.drawToBitmap(),
                "Unnamed project",
                data,
                false
            )
        )
        isMirrorMode = false
        hasSaved = true
    }
}