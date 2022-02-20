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
            FindAndReplaceFragment.newInstance(outerCanvasInstance.canvasFragment.myCanvasViewInstance.getNumberOfUniqueColors())
        currentFragmentInstance = findAndReplaceFragmentInstance
        navigateTo(
            supportFragmentManager,
            findAndReplaceFragmentInstance,
            R.id.activityCanvas_primaryFragmentHost,
            StringConstants.FRAGMENT_FIND_AND_REPLACE_TITLE,
            binding.activityCanvasPrimaryFragmentHost,
            binding.activityCanvasRootLayout
        )
        hideMenuItems()
    } else {
        binding.activityCanvasRootLayout.showSnackbar("You must have at least one color on your canvas to use this tool", SnackbarDuration.DEFAULT)
    }
}