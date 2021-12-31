package com.realtomjoney.pyxlmoose.activities.canvas

import android.util.Log

fun setPrimaryPixelColor(color: Int) {
    primaryColor = color
    binding.activityCanvasColorPrimaryView.setBackgroundColor(color)
}