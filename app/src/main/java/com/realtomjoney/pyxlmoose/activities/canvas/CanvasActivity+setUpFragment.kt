package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.fragments.canvas.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.setUpFragment() {
    canvasInstance = CanvasFragment.newInstance(spanCount)
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_canvasFragmentHost, canvasInstance).commit()
}