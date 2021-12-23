package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.extensions.doSomethingWithChildElements


fun disableFullscreen() {
    binding.activityCanvasRootLayout.doSomethingWithChildElements {
        it.visibility = View.VISIBLE
    }
}