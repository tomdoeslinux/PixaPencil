package com.realtomjoney.pyxlmoose.utility

import com.realtomjoney.pyxlmoose.models.XYPosition
import kotlin.math.ceil

object MathExtensions {
    fun reflectIndexHorizontally(index: Int, spanCount: Int) = ((((((ceil((index / spanCount).toDouble())) + spanCount) - (ceil((index / spanCount).toDouble()))) - (ceil((index / spanCount).toDouble()))) + (spanCount - 1) * (((ceil((index / spanCount).toDouble())) + spanCount) - (ceil((index / spanCount).toDouble())) - (ceil((index / spanCount).toDouble())))) + index.mod(spanCount) - spanCount).toInt()
    fun reflectIndexVertically(index: Int, spanCount: Int) = (index - (index.mod(spanCount))) + (spanCount - (index.mod(spanCount))) - 1

    fun convertListOfSizeNToListOfXYPosition(listSize: Int, spanCount: Int): MutableList<XYPosition> {
        val list = mutableListOf<XYPosition>()

        for (i in 0 until listSize) list.add(convertIndexToXYPosition(i, spanCount))

        return list
    }

    fun convertListToMap(listSize: Int, spanCount: Int): Map<XYPosition, Int> {
        val map = mutableMapOf<XYPosition, Int>()

        for (i in 0 until listSize) map[convertIndexToXYPosition(i, spanCount)] = i

        return map
    }

    fun convertIndexToXYPosition(index: Int, spanCount: Int): XYPosition {
        val div = (index + 1.0) / spanCount
        val positionX = ceil(div)
        val positionY = spanCount - index % spanCount
        return XYPosition(positionX.toInt(), positionY)
    }
}