package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import android.widget.Toast
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedSetUpFragment(savedGridState: List<View>? = null) {
    canvasFragmentInstance = CanvasFragment.newInstance(spanCount, true, savedGridState)
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragmentHost, canvasFragmentInstance).commit()
}