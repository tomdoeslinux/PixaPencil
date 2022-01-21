package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import com.realtomjoney.pyxlmoose.models.Coordinates

class ExpandToNeighborsAlgorithm(private val bitmap: Bitmap) {
    fun compute(seed: Coordinates): List<Coordinates> {
        val toReturn = mutableListOf<Coordinates>()

        if (seed.x > 0) toReturn.add(Coordinates(seed.x - 1, seed.y))

        if (seed.x < bitmap.width - 1) toReturn.add(Coordinates(seed.x + 1, seed.y))

        if (seed.y > 0) toReturn.add(Coordinates(seed.x, seed.y - 1))

        if (seed.y < bitmap.width - 1) toReturn.add(Coordinates(seed.x, seed.y + 1))

        return toReturn
    }
}