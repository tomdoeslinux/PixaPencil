package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.fragments.findandreplace.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

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
        binding.activityCanvasRootLayout.showSnackbar(StringConstants.SnackbarFindAndReplaceWarning, SnackbarDuration.DEFAULT)
    }
}