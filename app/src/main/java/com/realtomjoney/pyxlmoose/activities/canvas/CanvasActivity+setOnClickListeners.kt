package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringValues

fun CanvasActivity.openColorPickerDialog() {
    title = "Select Color"

    colorPickerFragmentInstance = initColorPickerFragmentInstance()

    binding.colorPickerFragmentHost.visibility = View.VISIBLE
    binding.colorPickerFragmentHost.bringToFront()
    (supportFragmentManager.beginTransaction()).replace(R.id.colorPickerFragmentHost, colorPickerFragmentInstance).commit()
    binding.doneButton.animate().scaleX(0f).scaleY(0f).setDuration(300).withEndAction { binding.doneButton.visibility = View.GONE }
}

fun CanvasActivity.extendedSetOnClickListeners() {
    binding.doneButton.setOnClickListener {
        if (index == -1) {
            PixelArtDatabase.addItem(
                PixelArt(
                    binding.fragmentHost.drawToBitmap(),
                    binding.titleTextView.text.toString(),
                    data,
                    false
                )
            )
            hasSaved = true
            (this as Activity).onBackPressed()
        } else {
            PixelArtDatabase.replaceItemByIndex(index!!, PixelArt(
                binding.fragmentHost.drawToBitmap(),
                binding.titleTextView.text.toString(),
                data,
                false
            ))
            hasSaved = true
            (this as Activity).onBackPressed()
        }
    }

    binding.colourSecondarySelected.setOnClickListener {
        isPrimaryColourSelected = false
        setPixelColour((binding.colourSecondarySelected.background as ColorDrawable).color)
    }

    binding.colourSecondarySelected.setOnLongClickListener {
        isPrimaryColourSelected = false
        openColorPickerDialog()
        true
    }


    binding.colourPrimarySelected.setOnClickListener {
        isPrimaryColourSelected = true
        setPixelColour((binding.colourPrimarySelected.background as ColorDrawable).color)
    }

    binding.colourPrimarySelected.setOnLongClickListener {
        isPrimaryColourSelected = true
        openColorPickerDialog()
        true
    }

    binding.horizontalMirrorButton.setOnClickListener {
        if (isVerticalMirrorEnabled) !isVerticalMirrorEnabled
        isHorizontalMirrorEnabled = !isHorizontalMirrorEnabled
    }
    binding.verticalMirrorButton.setOnClickListener {
        if (isHorizontalMirrorEnabled) !isHorizontalMirrorEnabled
        isVerticalMirrorEnabled = !isVerticalMirrorEnabled
    }

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

    binding.clearAllButton.setOnClickListener {
        showDialog(
            "Clear canvas",
            "Are you sure you want to clear the canvas? This cannot be undone.",
            "OK",
            { _, _ ->
                with(supportFragmentManager.beginTransaction()) {
                    remove(canvasFragmentInstance)
                    commit()
                }
                canvasFragmentInstance = CanvasFragment.newInstance(spanCount, true, null)
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentHost, canvasFragmentInstance).commit()
            }, "Back", { _, _ -> }, null)
    }

    binding.changeBackgroundButton.setOnClickListener {
        wantsToChangeBackground = !wantsToChangeBackground
    }

    binding.colorSwapButton.setOnClickListener {
        binding.colourPrimarySelected.setBackgroundColor(secondaryColour)
        binding.colourSecondarySelected.setBackgroundColor(primaryColour)

        val temp = primaryColour
        primaryColour = secondaryColour
        secondaryColour = temp
    }

    binding.findAndReplaceButton.setOnClickListener {
        findAndReplaceFragmentInstance = FindAndReplaceFragment.newInstance(extendedGetCanvasColors())

        title = "Find and Replace"

        binding.colorPickerFragmentHost.visibility = View.VISIBLE
        binding.colorPickerFragmentHost.bringToFront()
        (supportFragmentManager.beginTransaction()).replace(R.id.colorPickerFragmentHost, findAndReplaceFragmentInstance).commit()
        binding.doneButton.animate().scaleX(0f).scaleY(0f).setDuration(300).withEndAction { binding.doneButton.visibility = View.GONE }
    }

    binding.eraseButton.setOnClickListener {
        isErasing = !isErasing
    }
}