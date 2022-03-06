package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import com.realtomjoney.pyxlmoose.models.Coordinates

class ExpandToNeighborsAlgorithm(private val bitmap: Bitmap) {
    fun compute(seed: Coordinates): List<Coordinates> {
        val toReturn = mutableListOf<Coordinates>()

        val ex1 = Coordinates(seed.x - 1, seed.y)
        val ex2 = Coordinates(seed.x + 1, seed.y)
        val ex3 = Coordinates(seed.x, seed.y - 1)
        val ex4 = Coordinates(seed.x, seed.y + 1)

        if (seed.x > 0) {
            toReturn.add(ex1)
        }

        if (seed.x < bitmap.width - 1) {
            toReturn.add(ex2)
        }

        if (seed.y > 0) {
            toReturn.add(ex3)
        }

        if (seed.y < bitmap.height - 1) {
            toReturn.add(ex4)
        }

        return toReturn
    }
}