@file:Suppress("unused")

package com.realtomjoney.pyxlmoose.activities.canvas

import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase

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
        isErasing = false

        hasSaved = true
    }
}