package com.realtomjoney.pyxlmoose.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.realtomjoney.pyxlmoose.models.BitmapAction
import com.realtomjoney.pyxlmoose.models.BitmapActionData
import com.realtomjoney.pyxlmoose.models.XYPosition
import java.lang.Exception
import java.util.*

class FloodFillAlgorithm(private val bitmap: Bitmap, private val currentBitmapAction: BitmapAction, private val color: Int = Color.BLACK) {
    fun compute(seed: XYPosition) {
        val colorAtSeed = bitmap.getPixel(seed.x, seed.y)

        val queue = LinkedList<XYPosition>()

        queue.offer(seed)

        while (queue.isNotEmpty() && colorAtSeed != color) {
            val current = queue.poll()

            if (bitmap.getPixel(current!!.x, current.y) != colorAtSeed) {
                continue
            }

            currentBitmapAction.actionData.add(BitmapActionData(
                XYPosition(current.x, current.y),
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