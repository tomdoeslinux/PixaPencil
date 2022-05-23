package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment

fun CanvasActivity.setOnClickListeners() {
    brushesFragmentInstance = BrushesFragment.newInstance()
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_tabLayoutFragmentHost, brushesFragmentInstance!!).commit()

    filtersFragmentInstance = FiltersFragment.newInstance()
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_tabLayoutFragmentHost, filtersFragmentInstance!!).commit()

    colorPalettesFragmentInstance = ColorPalettesFragment.newInstance()
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_tabLayoutFragmentHost, colorPalettesFragmentInstance!!).commit()

    toolsFragmentInstance = ToolsFragment.newInstance()
    supportFragmentManager.beginTransaction().add(R.id.activityCanvas_tabLayoutFragmentHost, toolsFragmentInstance!!).commit()

    binding.activityCanvasTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            when (tab.position) {
                0 -> {
                    currentTab = 0
                    colorPalettesFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.GONE
                    toolsFragmentInstance!!.requireView().visibility = View.VISIBLE
                }

                1 -> {
                    currentTab = 1
                    toolsFragmentInstance!!.requireView().visibility = View.GONE
                    colorPalettesFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.VISIBLE
                }

                2 -> {
                    currentTab = 2
                    toolsFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.GONE
                    colorPalettesFragmentInstance!!.requireView().visibility = View.VISIBLE
                }

                3 -> {
                    currentTab = 3
                    colorPalettesFragmentInstance!!.requireView().visibility = View.GONE
                    filtersFragmentInstance!!.requireView().visibility = View.GONE
                    toolsFragmentInstance!!.requireView().visibility = View.GONE
                    brushesFragmentInstance!!.requireView().visibility = View.VISIBLE
                }
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab) { }

        override fun onTabUnselected(tab: TabLayout.Tab) { }
    })

    binding.activityCanvasColorSecondaryView.setOnClickListener {
        isPrimaryColorSelected = false
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
        setPixelColor((binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorPrimaryView.setOnLongPressListener {
        isPrimaryColorSelected = true
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
        supportFragmentManager.commit {
            replace(R.id.activityCanvas_primaryFragmentHost, ColorPickerFragment.newInstance(
                paramOldColor = primaryColor,
                paramColorPaletteMode = false))
            addToBackStack(null)
        }
    }


    binding.activityCanvasColorPrimaryView.setOnClickListener {
        isPrimaryColorSelected = true
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
        setPixelColor((binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorSecondaryView.setOnLongPressListener {
        isPrimaryColorSelected = false
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
        supportFragmentManager.commit {
            replace(R.id.activityCanvas_primaryFragmentHost, ColorPickerFragment.newInstance(
                paramOldColor = secondaryColor,
                paramColorPaletteMode = false))
            addToBackStack(null)
        }
    }
}