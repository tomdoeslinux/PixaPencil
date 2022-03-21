package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.utility.Flags

fun extendedOnPause() {
    currentTool = Tools.PENCIL_TOOL
    Flags.DisableActionMove = false
}