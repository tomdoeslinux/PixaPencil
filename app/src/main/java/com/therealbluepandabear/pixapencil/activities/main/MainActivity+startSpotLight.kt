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

package com.therealbluepandabear.pixapencil.activities.main

import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import com.takusemba.spotlight.OnSpotlightListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.LayoutTargetBinding

fun MainActivity.startSpotLight() {
    val layoutTargetBinding = LayoutTargetBinding.inflate(LayoutInflater.from(this))

    val firstTarget = Target.Builder()
        .setAnchor(binding.activityMainNewProjectButton)
        .setShape(Circle((binding.activityMainNewProjectButton.measuredWidth + 20).toFloat()))
        .setOverlay(layoutTargetBinding.root)
        .build()

    val spotlight = Spotlight.Builder(this)
        .setTargets(firstTarget)
        .setBackgroundColorRes(R.color.spotlightBackground)
        .setDuration(1000L)
        .setAnimation(DecelerateInterpolator(2f))
        .setOnSpotlightListener(object : OnSpotlightListener {
            override fun onStarted() {
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_main)
            }

            override fun onEnded() { }
        })
        .build()

    layoutTargetBinding.layoutTargetCloseButton.setOnClickListener {
        spotlight.finish()
        mainSpotlight = null
    }

    layoutTargetBinding.layoutTargetNextButton.isEnabled = false

    spotlight.start()
    this.mainSpotlight = spotlight
}