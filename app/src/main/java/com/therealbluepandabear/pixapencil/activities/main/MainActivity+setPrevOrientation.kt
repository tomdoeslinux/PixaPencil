package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.activities.canvas.prevOrientation

fun MainActivity.setPrevOrientation() {
    prevOrientation = resources.configuration.orientation
}