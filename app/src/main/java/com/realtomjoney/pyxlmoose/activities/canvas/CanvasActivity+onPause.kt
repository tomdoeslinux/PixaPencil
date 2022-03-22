package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.enums.Tools
import com.realtomjoney.pyxlmoose.utility.Flags

fun extendedOnPause() {
    currentTool = Tools.PencilTool
    Flags.DisableActionMove = false
}