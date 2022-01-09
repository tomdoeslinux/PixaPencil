package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.models.XYPosition
import com.realtomjoney.pyxlmoose.utility.MathExtensions

class ExpandToNeighborsAlgorithm {
    fun compute(spanCount: Int, from: XYPosition): List<Int> {
        val toReturn = mutableListOf<Int>()

        if (from.x > 1) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x - 1, from.y), spanCount))

        if (from.x < spanCount) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x + 1, from.y), spanCount))

        if (from.y > 1) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x, from.y - 1), spanCount))

        if (from.y < spanCount) toReturn.add(MathExtensions.convertXYPositionToIndex(XYPosition(from.x, from.y + 1), spanCount))

        return toReturn
    }
}