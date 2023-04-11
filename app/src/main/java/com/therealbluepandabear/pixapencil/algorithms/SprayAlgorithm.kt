/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.models.Coordinate
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

class SprayAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private var radius: Int, private var strength: Int) {
    fun compute(seed: Coordinate) {
        for (i in 0 until strength) {
            /** Thank you to to aioobe on StackOverflow for this solution.
             *
             * - [Link to aioobe' profile](https://stackoverflow.com/users/276052/aioobe)
             * - [Original StackOverFlow post](https://stackoverflow.com/questions/5837572/generate-a-random-point-within-a-circle-uniformly)
             * **/

            val r = radius * sqrt(Random.nextDouble(0.0, 2.0))
            val theta = Random.nextDouble(0.0, 2.0) * 2 * kotlin.math.PI

            val randX = (seed.x + r * cos(theta)).toInt()
            val randY = (seed.y + r * sin(theta)).toInt()

            val randCoordinate = Coordinate(randX, randY)

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(randCoordinate, algorithmInfo.color)
        }
    }
}