package com.realtomjoney.pyxlmoose.activities.canvas

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.graphics.ColorUtils
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.models.PixelArts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.openColorPickerDialog() {
    colorPickerFragmentInstance = initColorPickerFragmentInstance()
    currentFragmentInstance = colorPickerFragmentInstance
    navigateTo(supportFragmentManager, colorPickerFragmentInstance, R.id.colorPickerFragmentHost, "Select Color", binding.colorPickerFragmentHost, binding.rootLayout)
}

fun CanvasActivity.extendedSetOnClickListeners() {
    binding.activityCanvasDoneButton.setOnClickListener {
        if (index == -1) {
            CoroutineScope(Dispatchers.IO).launch {
                AppData.db.pixelArtCreationsDao().insertPixelArt(
                    PixelArts(
                        BitmapConverter.convertBitmapToString(binding.fragmentHost.drawToBitmap()),
                        binding.activityCanvasCanvasTitleEditText.text.toString(),
                        JsonConverter.convertPixelListToJsonString(dataAsListOfPixels()),
                        false
                    )
                )
            }
            hasSaved = true
            (this as Activity).onBackPressed()
        }
    }

    binding.activityCanvasColorSecondaryView.setOnClickListener {
        isPrimaryColourSelected = false
        setPixelColour((binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorPrimaryView.setOnLongClickListener {
        isPrimaryColourSelected = true
        openColorPickerDialog()
        true
    }


    binding.activityCanvasColorPrimaryView.setOnClickListener {
        isPrimaryColourSelected = true
        setPixelColour((binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorSecondaryView.setOnLongClickListener {
        isPrimaryColourSelected = false
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

    binding.activityCanvasColorSwapButton.setOnClickListener {
        binding.activityCanvasColorPrimaryView.setBackgroundColor(secondaryColour)
        binding.activityCanvasColorSecondaryView.setBackgroundColor(primaryColour)

        val temp = primaryColour
        primaryColour = secondaryColour
        secondaryColour = temp
    }

    binding.findAndReplaceButton.setOnClickListener {
        findAndReplaceFragmentInstance = FindAndReplaceFragment.newInstance(extendedGetCanvasColors())
        currentFragmentInstance = findAndReplaceFragmentInstance
        navigateTo(supportFragmentManager, findAndReplaceFragmentInstance, R.id.colorPickerFragmentHost, "Find and Replace", binding.colorPickerFragmentHost, binding.rootLayout)
    }

    binding.eraseButton.setOnClickListener {
        isErasing = !isErasing
    }

}