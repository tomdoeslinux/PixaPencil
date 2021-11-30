package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.MotionEvent

fun CanvasActivity.extendedOnTouchEvent(event: MotionEvent?): Boolean {
    return when (event!!.actionMasked) {
        MotionEvent.ACTION_MOVE -> {
            evaluate(event)
            true
        }
        else -> false
    }
}