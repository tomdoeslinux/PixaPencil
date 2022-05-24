package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.firstLoad
import com.therealbluepandabear.pixapencil.utility.constants.Flags

fun extendedOnPause() {
    Flags.DisableActionMove = false
    firstLoad = false
}