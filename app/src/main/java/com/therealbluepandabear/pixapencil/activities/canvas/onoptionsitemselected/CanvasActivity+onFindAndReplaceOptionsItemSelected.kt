package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import androidx.core.view.drawToBitmap
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.fragments.findandreplace.FindAndReplaceFragment

fun CanvasActivity.onFindAndReplaceOptionsItemSelected() {
    val uniqueColors = pixelGridViewInstance.getNumberOfUniqueColors()

    if (uniqueColors.isNotEmpty()) {
        supportFragmentManager.commit {
            replace(
                R.id.activityCanvas_primaryFragmentHost, FindAndReplaceFragment.newInstance(
                    paramCanvasColors = pixelGridViewInstance.getNumberOfUniqueColors(),
                    paramPixelGridViewBitmapSource = outerCanvasInstance.fragmentHost.drawToBitmap(),
                    paramTransparentBitmapSource = outerCanvasInstance.transparentBackgroundView.drawToBitmap(),
                    paramSelectedColorPaletteIndex = selectedColorPaletteIndex
                ))
            addToBackStack(null)
        }
    } else {
        binding.clayout?.showSnackbar(
            getString(R.string.snackbar_find_and_replace_warning_in_code_str),
            SnackbarDuration.Default)
    }
}