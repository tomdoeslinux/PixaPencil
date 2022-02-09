package com.realtomjoney.pyxlmoose.fragments.filters

import com.realtomjoney.pyxlmoose.utility.StringConstants

fun setOnClickListeners() {
    binding.apply {
        fragmentFiltersColorFilterButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.COLOR_FILTER_IDENTIFIER)
        }

        fragmentFiltersLightenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.LIGHTEN_FILTER_IDENTIFIER)
        }

        fragmentFiltersDarkenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.DARKEN_FILTER_IDENTIFIER)
        }

        fragmentFiltersInvertButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.INVERT_FILTER_IDENTIFIER)
        }

        fragmentFiltersInvertRedButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.INVERT_RED_FILTER_IDENTIFIER)
        }

        fragmentFiltersInvertGreenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.INVERT_GREEN_FILTER_IDENTIFIER)
        }

        fragmentFiltersInvertBlueButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.INVERT_BLUE_FILTER_IDENTIFIER)
        }

        fragmentFiltersGrayScaleButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.GRAYSCALE_FILTER_IDENTIFIER)
        }
    }
}