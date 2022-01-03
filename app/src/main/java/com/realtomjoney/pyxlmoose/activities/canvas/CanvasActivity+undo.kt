package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData

fun CanvasActivity.extendedUndo() {
    if (canvasStates.isNotEmpty()) deletedCanvasStates.add(canvasStates.last())

    if (canvasStates.size > 1) {
        canvasStates.remove(canvasStates.last())
        canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(canvasStates.last())
    } else if (canvasStates.size == 1 && index != -1) {
        canvasStates.remove(canvasStates.last())
        AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(context, {
            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(JsonConverter.convertJsonStringToPixelList((it[index!!]).pixelData))
        })
    }
    else {
        if (index == -1) {
            clearCanvas()
            canvasStates.clear()
        }
    }
    canvasFragmentInstance.myCanvasViewInstance.invalidate()
}