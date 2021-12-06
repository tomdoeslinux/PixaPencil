@file:Suppress("unused")

package com.realtomjoney.pyxlmoose.activities.canvas

fun CanvasActivity.extendedOnPause() {
    currentBackground = null
    hasSetBackgroundYet = false
    wantsToChangeBackground = false
}