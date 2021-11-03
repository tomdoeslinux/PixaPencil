package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedSetUpFragment() {
    canvasFragmentInstance = CanvasFragment.newInstance(spanCount, true, null)
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragmentHost, canvasFragmentInstance).commit()
}