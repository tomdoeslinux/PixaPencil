package com.therealbluepandabear.pixapencil.activities.main

import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.takusemba.spotlight.OnSpotlightListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import com.therealbluepandabear.pixapencil.R

fun MainActivity.startSpotLight() {
    val firstRoot = FrameLayout(this)
    val lyt = layoutInflater.inflate(R.layout.layout_target, firstRoot)

    val firstTarget = Target.Builder()
        .setAnchor(binding.activityMainNewProjectButton)
        .setShape(Circle((binding.activityMainNewProjectButton.measuredWidth + 20).toFloat()))
        .setOverlay(lyt)
        .build()

    val spotlight = Spotlight.Builder(this)
        .setTargets(firstTarget)
        .setBackgroundColorRes(R.color.spotlightBackground)
        .setDuration(1000L)
        .setAnimation(DecelerateInterpolator(2f))
        .setOnSpotlightListener(object : OnSpotlightListener {
            override fun onStarted() {
                lyt.findViewById<TextView>(R.id.layoutTarget_text).text = getString(R.string.spot_light_activity_main_in_code_str)
            }

            override fun onEnded() { }
        })
        .build()

    lyt.findViewById<Button>(R.id.layoutTarget_closeButton).setOnClickListener {
        spotlight.finish()
        mainSpotlight = null
    }

    lyt.findViewById<Button>(R.id.layoutTarget_nextButton).isEnabled = false

    spotlight.start()
    this.mainSpotlight = spotlight
}