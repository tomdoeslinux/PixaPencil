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

package com.therealbluepandabear.pixapencil.fragments.newproject

import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.shape.RoundedRectangle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.LayoutTargetBinding

fun NewProjectFragment.startSpotLight() {
    val layoutTargetBinding = LayoutTargetBinding.inflate(LayoutInflater.from(this.context))
    val targets = ArrayList<Target>()

    val firstTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasProjectTitleTextInputLayout)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_new_project_fragment_1)
            }
        })
        .setShape(RoundedRectangle((binding.fragmentNewCanvasProjectTitleTextInputLayout.measuredHeight + 100).toFloat(), (binding.root.measuredWidth).toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(firstTarget)

    val secondTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasWidthTextInputLayout)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_new_project_fragment_2)
            }

        })
        .setShape(RoundedRectangle((binding.fragmentNewCanvasWidthTextInputLayout.measuredHeight + 100).toFloat(), (binding.root.measuredWidth).toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(secondTarget)

    val thirdTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasHeightTextInputLayout)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_new_project_fragment_3)
            }

        })
        .setShape(RoundedRectangle(300f, 1400f, 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(thirdTarget)

    val fourthTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasDoneButton)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_new_project_fragment_4)
            }

        })
        .setShape(Circle((binding.fragmentNewCanvasDoneButton.measuredWidth).toFloat()))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(fourthTarget)

    val spotlight = Spotlight.Builder(this.requireActivity())
        .setTargets(targets)
        .setBackgroundColorRes(R.color.spotlightBackground)
        .setDuration(1000L)
        .setAnimation(DecelerateInterpolator(2f))
        .build()

    var current = 1

    layoutTargetBinding.layoutTargetNextButton.setOnClickListener {
        when (current) {
            1 -> {
                if (binding.fragmentNewCanvasProjectTitleTextInputEditText.text.toString().isNotBlank()) {
                    layoutTargetBinding.layoutTargetErrorText.text = ""
                    current++
                    spotlight.next()
                } else {
                    layoutTargetBinding.layoutTargetErrorText.text =
                        getString(R.string.spot_light_new_project_fragment_input_value, "'${getString(R.string.fragmentNewProject_name)}'")
                }
            }

            2 -> {
                if (binding.fragmentNewCanvasWidthTextInputEditText.text.toString().isNotBlank()) {
                    layoutTargetBinding.layoutTargetErrorText.text = ""
                    current++
                    spotlight.next()
                } else {
                    layoutTargetBinding.layoutTargetErrorText.text =
                        getString(R.string.spot_light_new_project_fragment_input_value, "'${getString(R.string.fragmentNewProject_width)}'")
                }
            }

            3 -> {
                if (binding.fragmentNewCanvasHeightTextInputEditText.text.toString().isNotBlank()) {
                    layoutTargetBinding.layoutTargetErrorText.text = ""
                    current++
                    spotlight.next()
                } else {
                    layoutTargetBinding.layoutTargetErrorText.text =
                        getString(R.string.spot_light_new_project_fragment_input_value,"'${getString(R.string.fragmentNewProject_height)}'")
                }
            }
        }
    }

    layoutTargetBinding.layoutTargetCloseButton.setOnClickListener {
        spotlight.finish()
        paramSpotLightInProgress = false
    }


    spotlight.start()
}