package com.realtomjoney.pyxlmoose.activities.canvas

import android.util.Log

fun extendedOnActionUp() {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
    Log.d("BEP", "Added")
}