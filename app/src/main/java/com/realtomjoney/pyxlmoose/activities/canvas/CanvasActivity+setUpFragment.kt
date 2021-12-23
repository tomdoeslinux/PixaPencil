package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.fragments.canvas.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedSetUpFragment() {
    canvasFragmentInstance = CanvasFragment.newInstance(spanCount)
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_canvasFragmentHost, canvasFragmentInstance).commit()
}