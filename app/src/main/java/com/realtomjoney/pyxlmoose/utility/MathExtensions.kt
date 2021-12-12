package com.realtomjoney.pyxlmoose.utility

import kotlin.math.ceil

object MathExtensions {
    fun reflectIndexHorizontally(index: Int, spanCount: Int) = ((((((ceil((index / spanCount).toDouble())) + spanCount) - (ceil((index / spanCount).toDouble()))) - (ceil((index / spanCount).toDouble()))) + (spanCount - 1) * (((ceil((index / spanCount).toDouble())) + spanCount) - (ceil((index / spanCount).toDouble())) - (ceil((index / spanCount).toDouble())))) + index.mod(spanCount) - spanCount).toInt()

    fun reflectIndexVertically(index: Int, spanCount: Int) = (index - (index.mod(spanCount))) + (spanCount - (index.mod(spanCount))) - 1
}