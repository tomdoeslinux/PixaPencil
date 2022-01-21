package com.realtomjoney.pyxlmoose.algorithms.shapes

import com.realtomjoney.pyxlmoose.models.Coordinates

interface ShapeAlgorithm {
    fun compute(p1: Coordinates, p2: Coordinates)
}