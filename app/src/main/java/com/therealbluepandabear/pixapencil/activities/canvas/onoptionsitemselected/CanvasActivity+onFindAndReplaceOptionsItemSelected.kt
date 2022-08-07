package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import androidx.core.view.drawToBitmap
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawPixelGridViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawTransparentBackgroundViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.getColors
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.replacecolor.ReplaceColorFragment

fun CanvasActivity.onFindAndReplaceOptionsItemSelected() {
    val paramScaledWidth =
        if (binding.activityCanvasPixelGridView.drawToBitmap().width <= drawPixelGridViewBitmap().width) {
            drawPixelGridViewBitmap().width * (drawPixelGridViewBitmap().width / binding.activityCanvasPixelGridView.drawToBitmap().width)
        } else {
            drawPixelGridViewBitmap().width * (binding.activityCanvasPixelGridView.drawToBitmap().width / drawPixelGridViewBitmap().width)
        }

    val paramScaledHeight =
        if (binding.activityCanvasPixelGridView.drawToBitmap().height <= drawPixelGridViewBitmap().height) {
            drawPixelGridViewBitmap().height * (drawPixelGridViewBitmap().height / binding.activityCanvasPixelGridView.drawToBitmap().height)
        } else {
            drawPixelGridViewBitmap().height * (binding.activityCanvasPixelGridView.drawToBitmap().height / drawPixelGridViewBitmap().height)
        }

    supportFragmentManager.commit {
        replace(
            R.id.activityCanvas_primaryFragmentHost, ReplaceColorFragment.newInstance(
                paramCanvasColors = binding.activityCanvasPixelGridView.pixelGridViewBitmap.getColors(),
                paramPixelGridViewBitmapSource = drawPixelGridViewBitmap(),
                paramTransparentBitmapSource = drawTransparentBackgroundViewBitmap(),
                paramSelectedColorPaletteIndex = selectedColorPaletteIndex,
                paramScaledWidth = paramScaledWidth,
                paramScaledHeight = paramScaledHeight
            )
        )
        addToBackStack(null)
    }
}