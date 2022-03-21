package com.realtomjoney.pyxlmoose.fragments.colorpicker.hsv

import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.colorPaletteMode_
import com.realtomjoney.pyxlmoose.utility.LongConstants

fun HSVColorPickerFragment.setOnClickListeners() {
    binding.fragmentHSVColorPickerDoneButton.setOnClickListener {
        hideKeyboard()
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                caller.onDoneButtonPressed((binding.fragmentHSVColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
            } catch (exception: Exception) {

            } finally {
                showMenuItems()
            }
        }, LongConstants.DefaultHandlerDelay)
    }
}