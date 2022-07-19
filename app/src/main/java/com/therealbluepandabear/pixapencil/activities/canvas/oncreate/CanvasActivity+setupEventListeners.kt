@file:Suppress("ClickableViewAccessibility")

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment

fun CanvasActivity.setupEventListeners() {
    binding.activityCanvasColorSwitcherView.setOnColorPickerTapped {
        // this 'if' is very important, as without it multiple fragments would be added to the backstack
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
}