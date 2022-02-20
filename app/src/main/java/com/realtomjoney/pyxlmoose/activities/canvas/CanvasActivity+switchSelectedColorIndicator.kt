package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View

fun switchSelectedColorIndicator() {
    if (isPrimaryColorSelected) {
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
    } else {
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
    }
}