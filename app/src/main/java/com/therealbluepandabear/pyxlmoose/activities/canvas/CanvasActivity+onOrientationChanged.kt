package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.fragments.outercanvas.OuterCanvasFragment

fun CanvasActivity.onOrientationChanged() {
    for (fragment in supportFragmentManager.fragments) {
        if (fragment is OuterCanvasFragment) {
            supportFragmentManager.beginTransaction()
                .remove(fragment).commit()
        }
    }

    outerCanvasInstance = OuterCanvasFragment.newInstance(width, height)
    supportFragmentManager.beginTransaction()
        .add(R.id.activityCanvas_outerCanvasFragmentHost, outerCanvasInstance).commit()
}