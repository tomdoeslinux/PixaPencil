package com.realtomjoney.pyxlmoose.fragments.filters

import com.realtomjoney.pyxlmoose.utility.StringConstants

fun setOnClickListeners() {
    binding.apply {
        fragmentFiltersColorFilterButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.ColorFilterIdentifier)
        }

        fragmentFiltersLightenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.LightenFilterIdentifier)
        }

        fragmentFiltersDarkenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.DarkenFilterIdentifier)
        }

        fragmentFiltersInvertButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.InvertFilterIdentifier)
        }

        fragmentFiltersGrayScaleButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.GrayscaleFilterIdentifier)
        }
    }

    binding.apply {
        fragmentFiltersColorFilterButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.ColorFilterIdentifier)
        }

        fragmentFiltersLightenButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.LightenFilterIdentifier)
        }

        fragmentFiltersDarkenButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.DarkenFilterIdentifier)
        }

        fragmentFiltersInvertButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.InvertFilterIdentifier)
        }

        fragmentFiltersGrayScaleButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.GrayscaleFilterIdentifier)
        }
    }
}