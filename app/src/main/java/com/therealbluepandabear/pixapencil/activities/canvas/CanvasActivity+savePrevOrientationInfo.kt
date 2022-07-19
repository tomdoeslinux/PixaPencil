package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.rotate
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import kotlinx.coroutines.launch

fun CanvasActivity.savePrevOrientationInfo() {
    if (prevOrientation != resources.configuration.orientation) {
        lifecycleScope.launch {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment is ActivityFragment) {
                    title = fragment.title
                }
            }

            if (prevRotation != 0) {
                rotate(prevRotation)
            }
        }
    }
}