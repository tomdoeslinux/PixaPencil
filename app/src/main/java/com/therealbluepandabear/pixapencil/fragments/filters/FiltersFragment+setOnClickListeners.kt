package com.therealbluepandabear.pixapencil.fragments.filters

import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun FiltersFragment.setOnClickListeners() {
    binding.fragmentFiltersColorFilterButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.COLOR_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersLightenButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.LIGHTEN_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersDarkenButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.DARKEN_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersInvertButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.INVERT_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersGrayScaleButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.GRAYSCALE_FILTER_IDENTIFIER)
    }


    binding.fragmentFiltersColorFilterButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.COLOR_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersLightenButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.LIGHTEN_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersDarkenButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.DARKEN_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersInvertButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.INVERT_FILTER_IDENTIFIER)
    }

    binding.fragmentFiltersGrayScaleButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.GRAYSCALE_FILTER_IDENTIFIER)
    }
}