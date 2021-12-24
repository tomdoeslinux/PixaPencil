package com.realtomjoney.pyxlmoose.fragments.colorpicker

import android.graphics.Color

fun ColorPickerFragment.setOnClickListeners() {
    binding.colorDoneButton.setOnClickListener {
        if (!colorPaletteMode) caller.onDoneButtonPressed(Color.argb(255, valueR, valueG, valueB))
        else caller.onDoneButtonPressed(Color.argb(255, valueR, valueG, valueB), true)
    }
}