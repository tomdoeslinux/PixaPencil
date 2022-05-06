package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment

fun CanvasActivity.onOrientationChanged() {
    for (fragment in supportFragmentManager.fragments) {
        if (fragment is OuterCanvasFragment) {
            supportFragmentManager.beginTransaction()
                .remove(fragment).commit()
        }
    }

    outerCanvasInstance = OuterCanvasFragment.newInstance(width, height, projectTitle)
    supportFragmentManager.beginTransaction()
        .add(R.id.activityCanvas_outerCanvasFragmentHost, outerCanvasInstance).commit()
}