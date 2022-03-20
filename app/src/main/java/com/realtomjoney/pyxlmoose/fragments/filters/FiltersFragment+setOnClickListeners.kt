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
}