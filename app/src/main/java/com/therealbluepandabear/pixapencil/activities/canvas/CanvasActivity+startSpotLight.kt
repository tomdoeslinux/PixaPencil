package com.therealbluepandabear.pixapencil.activities.canvas

import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.shape.RoundedRectangle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.LayoutTargetBinding

fun CanvasActivity.startSpotLight() {
    val layoutTargetBinding = LayoutTargetBinding.inflate(LayoutInflater.from(this))
    val targets = ArrayList<Target>()

    val firstTarget = Target.Builder()
        .setAnchor(binding.activityCanvasColorSwitcherView)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_1)
            }

        })
        .setShape(Circle(300f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(firstTarget)

    val secondTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y -= 400
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_2)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat(), binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(secondTarget)

    val thirdTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y -= 200
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_3)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat() + 300, binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(thirdTarget)

    val fourthTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y += 200
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_4)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat(), binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(fourthTarget)

    val fifthTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y -= 200
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_5)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat() + 300, binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(fifthTarget)

    val sixthTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y += 200
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_6)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat(), binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(sixthTarget)

    val seventhTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y -= 200
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_7)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat() + 300, binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(seventhTarget)

    val eighthTarget = Target.Builder()
        .setAnchor(binding.activityCanvasViewPager2)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() {}

            override fun onStarted() {
                layoutTargetBinding.root.y += 200
                layoutTargetBinding.layoutTargetText.text = getString(R.string.spot_light_activity_canvas_8)
            }

        })
        .setShape(RoundedRectangle(binding.activityCanvasViewPager2.measuredHeight.toFloat(), binding.activityCanvasViewPager2.measuredWidth.toFloat(), 20f))
        .setOverlay(layoutTargetBinding.root)
        .build()

    targets.add(eighthTarget)

    val spotlight = Spotlight.Builder(this)
        .setTargets(targets)
        .setBackgroundColorRes(R.color.spotlightBackground)
        .setDuration(1000L)
        .setAnimation(DecelerateInterpolator(2f))
        .build()

    layoutTargetBinding.layoutTargetNextButton.setOnClickListener {
        spotlight.next()
    }

    layoutTargetBinding.layoutTargetCloseButton.setOnClickListener {
        spotlight.finish()
    }

    spotlight.start()
}