package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.hideItems
import com.therealbluepandabear.pixapencil.extensions.navigateTo
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.fragments.findandreplace.FindAndReplaceFragment

fun CanvasActivity.findAndReplaceToolOnToolTapped() {
    val uniqueColors = pixelGridViewInstance.getNumberOfUniqueColors()

    if (uniqueColors.isNotEmpty()) {
        findAndReplaceFragmentInstance =
            FindAndReplaceFragment.newInstance(pixelGridViewInstance.getNumberOfUniqueColors(), getCoverImageBitmap(), selectedColorPaletteIndex)
        currentFragmentInstance = findAndReplaceFragmentInstance
        navigateTo(
            supportFragmentManager,
            findAndReplaceFragmentInstance,
            R.id.activityCanvas_primaryFragmentHost,
            getString(R.string.fragment_find_and_replace_title_in_code_str),
            binding.activityCanvasPrimaryFragmentHost,
            binding.activityCanvasRootLayout
        )
        menu.hideItems()
    } else {
        binding.activityCanvasRootLayout.showSnackbar(getString(R.string.snackbar_find_and_replace_warning_in_code_str), SnackbarDuration.Default)
    }
}