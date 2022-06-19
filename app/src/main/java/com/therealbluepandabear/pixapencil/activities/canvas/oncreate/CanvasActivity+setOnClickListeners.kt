package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment

@SuppressLint("ClickableViewAccessibility")
fun CanvasActivity.setOnClickListeners() {
    brushesFragmentInstance = BrushesFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .add(R.id.activityCanvas_tabLayoutFragmentHost, brushesFragmentInstance!!).commit()

    filtersFragmentInstance = FiltersFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .add(R.id.activityCanvas_tabLayoutFragmentHost, filtersFragmentInstance!!).commit()

    colorPalettesFragmentInstance = ColorPalettesFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .add(R.id.activityCanvas_tabLayoutFragmentHost, colorPalettesFragmentInstance!!).commit()

    toolsFragmentInstance = ToolsFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .add(R.id.activityCanvas_tabLayoutFragmentHost, toolsFragmentInstance!!).commit()

    binding.activityCanvasTabLayout.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            when (tab.position) {
                0 -> {
                    viewModel.currentTab = 0
                    colorPalettesFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.GONE
                    toolsFragmentInstance!!.requireView().visibility = View.VISIBLE
                }

                1 -> {
                    viewModel.currentTab = 1
                    toolsFragmentInstance!!.requireView().visibility = View.GONE
                    colorPalettesFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.VISIBLE
                }

                2 -> {
                    viewModel.currentTab = 2
                    toolsFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.GONE
                    colorPalettesFragmentInstance!!.requireView().visibility = View.VISIBLE
                }

                3 -> {
                    viewModel.currentTab = 3
                    colorPalettesFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.GONE
                    toolsFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab) {}

        override fun onTabUnselected(tab: TabLayout.Tab) {}
    })

    binding.activityCanvasColorSecondaryView.setOnClickListener {
        isPrimaryColorSelected = false
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
        setPixelColor((binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorPrimaryColorPickerIndicator?.setOnTouchListener { _, event ->
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (binding.activityCanvasColorSecondaryColorPickerIndicator?.drawToBitmap()
                        ?.getPixel(x.toInt(), y.toInt()) != Color.TRANSPARENT
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

    binding.activityCanvasColorSecondaryColorPickerIndicator?.setOnTouchListener { _, event ->
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (binding.activityCanvasColorSecondaryColorPickerIndicator?.drawToBitmap()?.getPixel(x.toInt(), y.toInt()) != Color.TRANSPARENT) {
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