package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.fragments.canvas.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.setUpFragment() {
    canvasInstance = if (index == -1) {
        CanvasFragment.newInstance(spanCount)
    } else {
        CanvasFragment.newInstance(spanCount, true)
    }
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_canvasFragmentHost, canvasInstance).commit()
}