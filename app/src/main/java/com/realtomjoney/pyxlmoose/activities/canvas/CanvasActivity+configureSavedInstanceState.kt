package com.realtomjoney.pyxlmoose.activities.canvas

import android.os.Bundle

fun CanvasActivity.configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(prevOrientationStr)
        prevBitmapStr = savedInstanceState.getString(prevBitmapStrStr)!!
    }
}