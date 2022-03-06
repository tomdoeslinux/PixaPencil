package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.fragments.outercanvas.OuterCanvasFragment

fun CanvasActivity.setUpFragment() {
    outerCanvasInstance = OuterCanvasFragment.newInstance(width, height)

    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_outerCanvasFragmentHost, outerCanvasInstance).commit()
}