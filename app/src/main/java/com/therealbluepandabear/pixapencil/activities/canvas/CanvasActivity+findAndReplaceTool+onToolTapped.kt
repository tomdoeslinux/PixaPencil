package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.hideItems
import com.therealbluepandabear.pixapencil.extensions.navigateTo
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.findandreplace.FindAndReplaceFragment
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.findAndReplaceToolOnToolTapped() {
    val uniqueColors = outerCanvasInstance.canvasFragment.myCanvasViewInstance.getNumberOfUniqueColors()

    if (uniqueColors.isNotEmpty()) {
        findAndReplaceFragmentInstance =
            FindAndReplaceFragment.newInstance(outerCanvasInstance.canvasFragment.myCanvasViewInstance.getNumberOfUniqueColors(), getCoverImageBitmap())
        currentFragmentInstance = findAndReplaceFragmentInstance
        navigateTo(
            supportFragmentManager,
            findAndReplaceFragmentInstance,
            R.id.activityCanvas_primaryFragmentHost,
            StringConstants.FragmentFindAndReplaceTitle,
            binding.activityCanvasPrimaryFragmentHost,
            binding.activityCanvasRootLayout
        )
        menu.hideItems()
    } else {
        binding.activityCanvasRootLayout.showSnackbar(StringConstants.SnackbarFindAndReplaceWarning, SnackbarDuration.Default)
    }
}