package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.utility.Flags

fun extendedOnPause() {
    currentTool = Tools.PencilTool
    Flags.DisableActionMove = false
}