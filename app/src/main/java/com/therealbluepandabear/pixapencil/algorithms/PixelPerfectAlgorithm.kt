package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.models.BitmapActionData
import com.therealbluepandabear.pixapencil.models.Coordinates

class PixelPerfectAlgorithm(private val algorithmInfo: AlgorithmInfoParameter) {
    fun compute(): MutableList<Coordinates> {
        val distinct = algorithmInfo.currentBitmapAction.actionData.distinctBy { it.coordinates }
        val data = mutableListOf<BitmapActionData>()

        var index = 0

        while (index < distinct.size) {
            if (index > 0 && index + 1 < distinct.size
                && (distinct[index - 1].coordinates.x == distinct[index].coordinates.x || distinct[index - 1].coordinates.y == distinct[index].coordinates.y)
                && (distinct[index + 1].coordinates.x == distinct[index].coordinates.x || distinct[index + 1].coordinates.y == distinct[index].coordinates.y)
                && distinct[index - 1].coordinates.x != distinct[index + 1].coordinates.x
                && distinct[index - 1].coordinates.y != distinct[index + 1].coordinates.y
            ) {
                index += 1
            }

            data.add(distinct[index])

            index += 1
        }

        val toReturn = mutableListOf<Coordinates>()

        for (i in data) {
            toReturn.add(i.coordinates)
        }

        return toReturn
    }
}