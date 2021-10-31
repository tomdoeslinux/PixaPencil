package com.realtomjoney.pyxlmoose.activity.canvas

import com.realtomjoney.pyxlmoose.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedSetUpFragment() {
    instance2 = CanvasFragment.newInstance(spanCount, true, null)
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragmentHost, instance2).commit()
}