package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.rotate
import kotlin.math.abs
import kotlin.math.ceil

/**
 * We are using 'ceil' to ensure that there is no crooked rotation effect, as when the user
 * selects '180 degrees', they are actually rotating the canvas by 179.9 degrees, I made this the behavior due to a
 * bug in the SDK in which when you rotate the canvas 180 degrees, the drop shadow is lost.
 * Thus, if we had no 'ceil', the resulting bitmap would be crooked as for some reason it rounds it down in this situation.
 */

fun CanvasActivity.drawPixelGridViewBitmap(): Bitmap {
    return binding.activityCanvasPixelGridView.pixelGridViewBitmap
        .rotate(ceil(abs(binding.activityCanvasCardView.rotation)).toInt(), viewModel.flipMatrix)
}