package com.realtomjoney.pyxlmoose.activity.canvas

import com.realtomjoney.pyxlmoose.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedSetUpFragment() {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, true, null)).commit()
}