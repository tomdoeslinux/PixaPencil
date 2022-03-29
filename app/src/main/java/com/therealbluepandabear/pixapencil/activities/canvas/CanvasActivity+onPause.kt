package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.utility.Flags

fun extendedOnPause() {
    currentTool = Tools.PencilTool
    Flags.DisableActionMove = false
}