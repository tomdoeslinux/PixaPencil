/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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