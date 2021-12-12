package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.models.Pixel

fun CanvasActivity.extendedSetUpFragment() {
    canvasFragmentInstance = CanvasFragment.newInstance(spanCount)
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_canvasFragmentHost, canvasFragmentInstance).commit()
}