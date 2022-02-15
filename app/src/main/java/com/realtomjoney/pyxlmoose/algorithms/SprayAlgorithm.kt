package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.IntConstants
import kotlin.math.floor
import kotlin.random.Random

class SprayAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, var radius: Int = IntConstants.DEF_RADIUS_SIZE, var strength: Int = IntConstants.DEF_STRENGTH_SIZE) {
    fun compute(seed: Coordinates) {
        val ct = floor((radius / 2).toDouble()).toInt()

        val p1 = Coordinates(seed.x + ct, seed.y + ct)
        val p2 = Coordinates(seed.x - ct, seed.y - ct)

        for (i in 0 until strength) {
            val rn1 = Random.nextInt(-ct, ct)
            val rn2 = Random.nextInt(-ct, ct)
            val rn3 = Random.nextInt(-ct, ct)
            val rn4 = Random.nextInt(-ct, ct)

            val randCoordinates = Coordinates(
                Random.nextInt(
                    p2.x + rn1,
                    p1.x + rn2
                ), Random.nextInt(
                    p2.y + rn3,
                    p1.y + rn4))

            outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(
                randCoordinates.x, randCoordinates.y, algorithmInfo.color
            )
        }
    }
}