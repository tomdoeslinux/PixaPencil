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