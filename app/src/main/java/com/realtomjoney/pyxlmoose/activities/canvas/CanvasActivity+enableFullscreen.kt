package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.extensions.doSomethingWithChildElements

fun enableFullscreen() {
    binding.activityCanvasRootLayout.doSomethingWithChildElements {
        it.visibility = View.GONE
    }
    binding.activityCanvasCanvasFragmentHostCardViewParent.visibility = View.VISIBLE
}