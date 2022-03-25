package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.enums.SnackbarDuration
import com.therealbluepandabear.pyxlmoose.extensions.navigateTo
import com.therealbluepandabear.pyxlmoose.extensions.showSnackbar
import com.therealbluepandabear.pyxlmoose.fragments.findandreplace.FindAndReplaceFragment
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

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
        hideMenuItems()
    } else {
        binding.activityCanvasRootLayout.showSnackbar(StringConstants.SnackbarFindAndReplaceWarning, SnackbarDuration.Default)
    }
}