package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import java.util.*

class FloodFillAlgorithm(private val algorithmInfo: AlgorithmInfoParameter) {
    fun compute(seed: Coordinates) {
        val colorAtSeed = algorithmInfo.bitmap.getPixel(seed)

        val queue = LinkedList<Coordinates>()

        queue.offer(seed)

        while (queue.isNotEmpty() && colorAtSeed != algorithmInfo.color) {
            val current = queue.poll()

            if (algorithmInfo.bitmap.getPixel(current!!) != colorAtSeed) {
                continue
            }

            algorithmInfo.canvasCommandsHelperInstance.overrideSetPixel(current, algorithmInfo.color, true)

            val expandToNeighborsAlgorithmInstance = ExpandToNeighborsAlgorithm(algorithmInfo.bitmap)
            for (index in expandToNeighborsAlgorithmInstance.compute(current)) {
                queue.offer(index)
            }
        }
    }
}