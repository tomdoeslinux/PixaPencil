package com.therealbluepandabear.pixapencil.fragments.newproject

import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.shape.RoundedRectangle
import com.therealbluepandabear.pixapencil.R

fun NewProjectFragment.startSpotLight() {
    val firstRoot = FrameLayout(this.requireContext())
    val lyt = layoutInflater.inflate(R.layout.layout_target, firstRoot)
    val text = lyt.findViewById<TextView>(R.id.layoutTarget_text)
    val targets = ArrayList<Target>()

    val firstTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasProjectTitleTextInputLayout)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                text.text = getString(R.string.spot_light_new_project_fragment_1_in_code_str)
            }
        })
        .setShape(RoundedRectangle((binding.fragmentNewCanvasProjectTitleTextInputLayout.measuredHeight + 100).toFloat(), (binding.root.measuredWidth ).toFloat(), 20f))
        .setOverlay(lyt)
        .build()

    targets.add(firstTarget)

    val secondTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasWidthTextInputLayout)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                text.text = getString(R.string.spot_light_new_project_fragment_2_in_code_str)
            }

        })
        .setShape(RoundedRectangle((binding.fragmentNewCanvasWidthTextInputLayout.measuredHeight + 100).toFloat(), (binding.root.measuredWidth).toFloat(), 20f))
        .setOverlay(lyt)
        .build()

    targets.add(secondTarget)

    val thirdTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasHeightTextInputLayout)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                text.text = getString(R.string.spot_light_new_project_fragment_3_in_code_str)
            }

        })
        .setShape(RoundedRectangle(300f, 1400f, 20f))
        .setOverlay(lyt)
        .build()

    targets.add(thirdTarget)

    val fourthTarget = Target.Builder()
        .setAnchor(binding.fragmentNewCanvasDoneButton)
        .setOnTargetListener(object : OnTargetListener {
            override fun onEnded() { }

            override fun onStarted() {
                text.text = getString(R.string.spot_light_new_project_fragment_4_in_code_str)
            }

        })
        .setShape(Circle((binding.fragmentNewCanvasDoneButton.measuredWidth).toFloat()))
        .setOverlay(lyt)
        .build()

    targets.add(fourthTarget)

    val spotlight = Spotlight.Builder(this.requireActivity())
        .setTargets(targets)
        .setBackgroundColorRes(R.color.spotlightBackground)
        .setDuration(1000L)
        .setAnimation(DecelerateInterpolator(2f))
        .build()

    lyt.findViewById<Button>(R.id.layoutTarget_nextButton).setOnClickListener {
        spotlight.next()
    }

    lyt.findViewById<Button>(R.id.layoutTarget_closeButton).setOnClickListener {
        spotlight.finish()
        paramSpotLightInProgress = false
    }


    spotlight.start()
}