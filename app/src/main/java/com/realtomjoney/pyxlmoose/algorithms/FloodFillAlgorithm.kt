package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.Coordinates
import java.util.*

class FloodFillAlgorithm(private val bitmap: Bitmap, private val currentBitmapAction: BitmapAction, private val color: Int = Color.BLACK) {
    fun compute(seed: Coordinates) {
        val colorAtSeed = bitmap.getPixel(seed.x, seed.y)

        val queue = LinkedList<Coordinates>()

        queue.offer(seed)

        while (queue.isNotEmpty() && colorAtSeed != color) {
            val current = queue.poll()

            if (bitmap.getPixel(current!!.x, current.y) != colorAtSeed) {
                continue
            }

            currentBitmapAction.actionData.add(BitmapActionData(
                Coordinates(current.x, current.y),
                bitmap.getPixel(current.x, current.y),
            ))
            bitmap.setPixel(current.x, current.y, color)

            val expandToNeighborsAlgorithmInstance = ExpandToNeighborsAlgorithm(bitmap)
            for (index in expandToNeighborsAlgorithmInstance.compute(current)) {
                queue.offer(index)
            }
        }
    }
}