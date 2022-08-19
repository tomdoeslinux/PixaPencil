package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

class SprayAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private var radius: Int, private var strength: Int) {
    fun compute(seed: Coordinates) {
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

            val randCoordinates = Coordinates(randX, randY)

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(randCoordinates, algorithmInfo.color)
        }
    }
}