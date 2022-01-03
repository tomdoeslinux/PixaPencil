package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedRedo() {
    if (deletedCanvasStates.isNotEmpty()) {
        canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(deletedCanvasStates.last())
        canvasStates.add(deletedCanvasStates.last())
        deletedCanvasStates.remove(deletedCanvasStates.last())

        canvasFragmentInstance.myCanvasViewInstance.invalidate()
    }
}