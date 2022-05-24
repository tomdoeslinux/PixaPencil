package com.therealbluepandabear.pixapencil.fragments.filters

import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun FiltersFragment.setOnClickListeners() {
    binding.fragmentFiltersColorFilterButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.ColorFilterIdentifier)
    }

    binding.fragmentFiltersLightenButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.LightenFilterIdentifier)
    }

    binding.fragmentFiltersDarkenButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.DarkenFilterIdentifier)
    }

    binding.fragmentFiltersInvertButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.InvertFilterIdentifier)
    }

    binding.fragmentFiltersGrayScaleButton.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.GrayscaleFilterIdentifier)
    }


    binding.fragmentFiltersColorFilterButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.ColorFilterIdentifier)
    }

    binding.fragmentFiltersLightenButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.LightenFilterIdentifier)
    }

    binding.fragmentFiltersDarkenButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.DarkenFilterIdentifier)
    }

    binding.fragmentFiltersInvertButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.InvertFilterIdentifier)
    }

    binding.fragmentFiltersGrayScaleButtonH.setOnClickListener {
        caller.onFilterTapped(StringConstants.Identifiers.GrayscaleFilterIdentifier)
    }
}