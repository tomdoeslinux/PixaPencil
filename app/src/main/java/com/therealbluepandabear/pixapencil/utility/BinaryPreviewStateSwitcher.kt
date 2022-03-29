package com.therealbluepandabear.pixapencil.utility

import com.therealbluepandabear.pixapencil.activities.canvas.primaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.models.BitmapAction

object BinaryPreviewStateSwitcher {
    private var binaryPairStateData: Pair<BitmapAction?, BitmapAction?> = Pair(null, null)


    fun clear() {
        binaryPairStateData = binaryPairStateData.copy(null, null)
    }

    fun feedState(state: BitmapAction) {
        if (binaryPairStateData.first == null && binaryPairStateData.second == null) {
            binaryPairStateData = binaryPairStateData.copy(first = state)
        } else if (binaryPairStateData.first != null && binaryPairStateData.second == null) {
            binaryPairStateData = binaryPairStateData.copy(second = state)
        } else {
            binaryPairStateData = binaryPairStateData.copy(first = null, second = null)
            feedState(state)
        }
    }
    fun switch() {
        if (binaryPairStateData.second != null) {
            for ((key, value) in binaryPairStateData.second!!.actionData.distinctBy { it.coordinates }) {
                primaryAlgorithmInfoParameter.bitmap.setPixel(key.x, key.y, value)
            }
        } else {
            clear()
        }
    }
}