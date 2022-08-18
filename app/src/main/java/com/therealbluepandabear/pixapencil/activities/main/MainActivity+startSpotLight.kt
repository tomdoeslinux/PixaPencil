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