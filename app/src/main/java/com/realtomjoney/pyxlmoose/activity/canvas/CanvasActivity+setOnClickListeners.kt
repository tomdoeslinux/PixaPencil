package com.realtomjoney.pyxlmoose.activity.canvas

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.core.graphics.ColorUtils
import androidx.core.view.drawToBitmap
import androidx.fragment.app.FragmentTransaction
import com.realtomjoney.pyxlmoose.PixelArt
import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.StringValues

fun CanvasActivity.openColorPickerDialog() {
    title = "Select Color"

    instance = initColorPickerFragmentInstance()

    binding.colorPickerFragmentHost.visibility = View.VISIBLE
    binding.colorPickerFragmentHost.bringToFront()
    (supportFragmentManager.beginTransaction()).replace(R.id.colorPickerFragmentHost, instance).commit()
    binding.doneButton.animate().scaleX(0f).scaleY(0f).setDuration(300).withEndAction { binding.doneButton.visibility = View.GONE }
}

fun CanvasActivity.extendedSetOnClickListeners() {
    binding.doneButton.setOnClickListener {
        if (binding.titleTextView.text.toString().isNotBlank()) {
            PixelArtDatabase.addItem(PixelArt(binding.fragmentHost.drawToBitmap(), binding.titleTextView.text.toString(), data, false))
            isMirrorMode = false
            hasSaved = true
            (this as Activity).onBackPressed()
        } else {
            Toast.makeText(this, StringValues.MESSAGE_NAME_PROJECT, Toast.LENGTH_SHORT).show()
        }
    }

    binding.colourSecondarySelected.setOnClickListener {
        isPrimaryColourSelected = false
        setPixelColour((binding.colourSecondarySelected.background as ColorDrawable).color)
    }

    binding.colourSecondarySelected.setOnLongClickListener {
        setPixelColour(secondaryColour)
        openColorPickerDialog()
        true
    }


    binding.colourPrimarySelected.setOnClickListener {
        isPrimaryColourSelected = true
        setPixelColour((binding.colourPrimarySelected.background as ColorDrawable).color)
    }

    binding.colourPrimarySelected.setOnLongClickListener {
        setPixelColour(primaryColour)
        openColorPickerDialog()
        true
    }

    binding.mirrorButton.setOnClickListener { isMirrorMode = !isMirrorMode }

    binding.darkenButton.setOnClickListener {
        if (isPrimaryColourSelected) setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.BLACK, 0.2f))
        else setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.BLACK, 0.2f))
    }

    binding.lightenButton.setOnClickListener {
        if (isPrimaryColourSelected) setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.WHITE, 0.2f))
        else setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.WHITE, 0.2f))
    }

    binding.colorPickerButton.setOnClickListener {
        colorPickerMode = !colorPickerMode
    }
}