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
import com.realtomjoney.pyxlmoose.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class Tools {
    PENCIL_TOOL, HORIZONTAL_MIRROR_TOOL, VERTICAL_MIRROR_TOOL, DARKEN_TOOL, LIGHTEN_TOOL, CHANGE_BACKGROUND_TOOL, COLOR_PICKER_TOOL, ERASE_TOOL
}

var currentTool: Tools? = null

fun CanvasActivity.openColorPickerDialog() {
    colorPickerFragmentInstance = initColorPickerFragmentInstance()
    currentFragmentInstance = colorPickerFragmentInstance
    navigateTo(supportFragmentManager, colorPickerFragmentInstance, R.id.activityCanvas_colorPickerFragmentHost, "Select Color", binding.activityCanvasColorPickerFragmentHost, binding.activityCanvasRootLayout)
}

fun CanvasActivity.extendedSetOnClickListeners() {
    binding.activityCanvasDoneButton.setOnClickListener {
        if (index == -1) {
            CoroutineScope(Dispatchers.IO).launch {
                AppData.db.pixelArtCreationsDao().insertPixelArt(
                    PixelArt(
                        BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()),
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
        isPrimaryColorSelected = false
        setPixelColor((binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorPrimaryView.setOnLongClickListener {
        isPrimaryColorSelected = true
        openColorPickerDialog()
        true
    }


    binding.activityCanvasColorPrimaryView.setOnClickListener {
        isPrimaryColorSelected = true
        setPixelColor((binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
    }

    binding.activityCanvasColorSecondaryView.setOnLongClickListener {
        isPrimaryColorSelected = false
        openColorPickerDialog()
        true
    }

    binding.activityCanvasPencilButton.setOnClickListener {
        currentTool = Tools.PENCIL_TOOL
    }

    binding.activityCanvasHorizontalMirrorButton.setOnClickListener {
        currentTool = Tools.HORIZONTAL_MIRROR_TOOL
    }
    binding.activityCanvasVerticalMirrorButton.setOnClickListener {
        currentTool = Tools.VERTICAL_MIRROR_TOOL
    }

    binding.activityCanvasDarkenButton.setOnClickListener {
        if (isPrimaryColorSelected) setPixelColor(ColorUtils.blendARGB(getSelectedColor(), Color.BLACK, 0.2f))
        else setPixelColor(ColorUtils.blendARGB(getSelectedColor(), Color.BLACK, 0.2f))

        currentTool = Tools.DARKEN_TOOL
    }

    binding.activityCanvasLightenButton.setOnClickListener {
        if (isPrimaryColorSelected) setPixelColor(ColorUtils.blendARGB(getSelectedColor(), Color.WHITE, 0.2f))
        else setPixelColor(ColorUtils.blendARGB(getSelectedColor(), Color.WHITE, 0.2f))

        currentTool = Tools.LIGHTEN_TOOL
    }

    binding.activityCanvasColorPickerButton.setOnClickListener {
        currentTool = Tools.COLOR_PICKER_TOOL
    }

    binding.activityCanvasResetCanvasButton.setOnClickListener {
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
                    .add(R.id.activityCanvas_canvasFragmentHost, canvasFragmentInstance).commit()
            }, "Back", { _, _ -> }, null)
    }

    binding.activityCanvasChangeBackgroundButton.setOnClickListener {
        currentTool = Tools.CHANGE_BACKGROUND_TOOL
    }

    binding.activityCanvasColorSwapButton.setOnClickListener {
        binding.activityCanvasColorPrimaryView.setBackgroundColor(secondaryColor)
        binding.activityCanvasColorSecondaryView.setBackgroundColor(primaryColor)

        val temp = primaryColor
        primaryColor = secondaryColor
        secondaryColor = temp
    }

    binding.activityCanvasFindAndReplaceButton.setOnClickListener {
        findAndReplaceFragmentInstance = FindAndReplaceFragment.newInstance(extendedGetCanvasColors())
        currentFragmentInstance = findAndReplaceFragmentInstance
        navigateTo(supportFragmentManager, findAndReplaceFragmentInstance, R.id.activityCanvas_colorPickerFragmentHost, "Find and Replace", binding.activityCanvasColorPickerFragmentHost, binding.activityCanvasRootLayout)
    }

    binding.activityCanvasEraseButton.setOnClickListener {
        currentTool = Tools.ERASE_TOOL
    }

}