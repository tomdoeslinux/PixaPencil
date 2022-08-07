package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import androidx.core.view.drawToBitmap
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawPixelGridViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawTransparentBackgroundViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.extensions.getColors
import com.therealbluepandabear.pixapencil.extensions.rotate
import com.therealbluepandabear.pixapencil.fragments.replacecolor.ReplaceColorFragment
import kotlin.math.abs
import kotlin.math.ceil

fun CanvasActivity.onFindAndReplaceOptionsItemSelected() {
    supportFragmentManager.commit {
        replace(
            R.id.activityCanvas_primaryFragmentHost, ReplaceColorFragment.newInstance(
                paramCanvasColors = binding.activityCanvasPixelGridView.pixelGridViewBitmap.getColors(),
                paramPixelGridViewBitmapSource = drawPixelGridViewBitmap(),
                paramTransparentBitmapSource = drawTransparentBackgroundViewBitmap(),
                paramSelectedColorPaletteIndex = selectedColorPaletteIndex,
                paramScaledWidth = binding.activityCanvasPixelGridView.drawToBitmap().rotate(ceil(abs(binding.activityCanvasCardView.rotation)).toInt(), viewModel.flipMatrix).width,
                paramScaledHeight =  binding.activityCanvasPixelGridView.drawToBitmap().rotate(ceil(abs(binding.activityCanvasCardView.rotation)).toInt(), viewModel.flipMatrix).height
            )
        )
        addToBackStack(null)
    }
}