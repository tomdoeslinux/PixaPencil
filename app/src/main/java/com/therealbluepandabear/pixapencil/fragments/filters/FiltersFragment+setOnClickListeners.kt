package com.therealbluepandabear.pixapencil.fragments.filters

import com.therealbluepandabear.pixapencil.utility.StringConstants

fun setOnClickListeners() {
    binding.apply {
        fragmentFiltersColorFilterButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.ColorFilterIdentifier)
        }

        fragmentFiltersLightenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.LightenFilterIdentifier)
        }

        fragmentFiltersDarkenButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.DarkenFilterIdentifier)
        }

        fragmentFiltersInvertButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.InvertFilterIdentifier)
        }

        fragmentFiltersGrayScaleButton.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.GrayscaleFilterIdentifier)
        }
    }

    binding.apply {
        fragmentFiltersColorFilterButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.ColorFilterIdentifier)
        }

        fragmentFiltersLightenButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.LightenFilterIdentifier)
        }

        fragmentFiltersDarkenButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.DarkenFilterIdentifier)
        }

        fragmentFiltersInvertButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.InvertFilterIdentifier)
        }

        fragmentFiltersGrayScaleButtonH.setOnClickListener {
            caller.onFilterTapped(StringConstants.Identifiers.GrayscaleFilterIdentifier)
        }
    }
}