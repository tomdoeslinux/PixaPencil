package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import com.realtomjoney.pyxlmoose.models.Coordinates

class ExpandToNeighborsAlgorithm(private val bitmap: Bitmap) {
    fun compute(from: Coordinates): List<Coordinates> {
        val toReturn = mutableListOf<Coordinates>()

        if (from.x > 0) toReturn.add(Coordinates(from.x - 1, from.y))

        if (from.x < bitmap.width - 1) toReturn.add(Coordinates(from.x + 1, from.y))

        if (from.y > 0) toReturn.add(Coordinates(from.x, from.y - 1))

        if (from.y < bitmap.width - 1) toReturn.add(Coordinates(from.x, from.y + 1))

        return toReturn
    }
}