package com.realtomjoney.pyxlmoose.fragments.colorpicker

import android.graphics.Color
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems

fun ColorPickerFragment.setOnClickListeners() {
    binding.colorDoneButton.setOnClickListener {
        try {
            if (!colorPaletteMode) caller.onDoneButtonPressed(
                Color.argb(
                    255,
                    valueR,
                    valueG,
                    valueB
                )
            )
            else caller.onDoneButtonPressed(Color.argb(255, valueR, valueG, valueB), true)
        } catch (ex: Exception) {
        }
        finally {
            showMenuItems()
        }
    }
}