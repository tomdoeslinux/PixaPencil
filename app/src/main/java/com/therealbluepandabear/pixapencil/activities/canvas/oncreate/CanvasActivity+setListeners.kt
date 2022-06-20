@file:Suppress("ClickableViewAccessibility")

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment

fun CanvasActivity.setListeners() {
    binding.activityCanvasColorSecondaryView.setOnClickListener {
        isPrimaryColorSelected = false
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
        setPixelColor((binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorPrimaryColorPickerIndicator.setOnTouchListener { _, event ->
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (binding.activityCanvasColorSecondaryColorPickerIndicator.drawToBitmap()
                    .getPixel(x.toInt(), y.toInt()) != Color.TRANSPARENT
                ) {
                    isPrimaryColorSelected = true
                    binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
                    binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
                    supportFragmentManager.commit {
                        replace(
                            R.id.activityCanvas_primaryFragmentHost,
                            ColorPickerFragment.newInstance(
                                paramOldColor = viewModel.primaryColor,
                                paramColorPalette = null
                            )
                        )
                        addToBackStack(null)
                    }
                }
            }
        }
        false
    }


    binding.activityCanvasColorPrimaryView.setOnClickListener {
        isPrimaryColorSelected = true
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
        setPixelColor((binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorSecondaryColorPickerIndicator.setOnTouchListener { _, event ->
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (binding.activityCanvasColorSecondaryColorPickerIndicator.drawToBitmap().getPixel(x.toInt(), y.toInt()) != Color.TRANSPARENT) {
                    isPrimaryColorSelected = false
                    binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
                    binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
                    supportFragmentManager.commit {
                        replace(
                            R.id.activityCanvas_primaryFragmentHost,
                            ColorPickerFragment.newInstance(
                                paramOldColor = viewModel.secondaryColor,
                                paramColorPalette = null
                            )
                        )
                        addToBackStack(null)
                    }
                }
            }
        }
        false
    }
}