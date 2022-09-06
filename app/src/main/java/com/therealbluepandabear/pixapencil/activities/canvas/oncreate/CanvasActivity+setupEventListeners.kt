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

@file:Suppress("ClickableViewAccessibility")

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment

private fun CanvasActivity.navigateToColorPicker() {
    if (supportFragmentManager.backStackEntryCount == 0) {
        supportFragmentManager.commit {
            replace(
                R.id.activityCanvas_primaryFragmentHost,
                ColorPickerFragment.newInstance(
                    paramOldColor = getSelectedColor(),
                    paramColorPalette = null
                )
            )
            addToBackStack(null)
        }
    }
}

fun CanvasActivity.setupEventListeners() {
    binding.activityCanvasColorSwitcherView.setOnColorPickerTapped {
        navigateToColorPicker()
    }

    binding.activityCanvasColorSwitcherView.setOnPrimaryColorLongTapped {
        navigateToColorPicker()
    }

    binding.activityCanvasColorSwitcherView.setOnSecondaryColorLongTapped {
        navigateToColorPicker()
    }
}