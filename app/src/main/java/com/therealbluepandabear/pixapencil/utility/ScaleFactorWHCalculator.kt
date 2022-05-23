package com.therealbluepandabear.pixapencil.utility

import android.content.res.Configuration
import android.content.res.Resources
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.models.ScaleFactorWHInfo

object ScaleFactorWHCalculator {
    private fun whenLandscape(width: Int, height: Int): ScaleFactorWHInfo {
        when {
            width == height -> {
                return ScaleFactorWHInfo(
                    binding.root.measuredHeight,
                    binding.root.measuredHeight
                )
            }

            width > height -> {
                val scaleFactorW = binding.root.measuredHeight

                val ratio = height.toDouble() / width.toDouble()

                val scaleFactorH = (scaleFactorW * ratio).toInt()

                return ScaleFactorWHInfo(scaleFactorW, scaleFactorH)
            }

            else -> {
                val scaleFactorH = binding.root.measuredHeight

                val ratio = width.toDouble() / height.toDouble()

                val scaleFactorW = (scaleFactorH * ratio).toInt()

                return ScaleFactorWHInfo(scaleFactorW, scaleFactorH)
            }
        }
    }

    private fun whenPortrait(resources: Resources, width: Int, height: Int): ScaleFactorWHInfo {
        when {
            width == height -> {
                return ScaleFactorWHInfo(
                    resources.displayMetrics.widthPixels,
                    resources.displayMetrics.widthPixels
                )
            }

            width > height -> {
                val scaleFactorW = binding.root.measuredWidth

                val ratio = height.toDouble() / width.toDouble()

                val scaleFactorH = (scaleFactorW * ratio).toInt()

                return ScaleFactorWHInfo(scaleFactorW, scaleFactorH)
            }

            else -> {
                val scaleFactorH = binding.root.measuredWidth

                val ratio = width.toDouble() / height.toDouble()

                val scaleFactorW = (scaleFactorH * ratio).toInt()

                return ScaleFactorWHInfo(scaleFactorW, scaleFactorH)
            }
        }
    }

    fun calculate(width: Int, height: Int, orientation: Int, resources: Resources): ScaleFactorWHInfo {
        return when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                whenLandscape(width, height)
            }

            Configuration.ORIENTATION_PORTRAIT -> {
               whenPortrait(resources, width, height)
            }

            else -> {
                whenPortrait(resources, width, height)
            }
        }
    }
}