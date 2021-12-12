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
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private fun CanvasActivity.openColorPickerDialog() {
    colorPickerFragmentInstance = initColorPickerFragmentInstance()
    currentFragmentInstance = colorPickerFragmentInstance
    navigateTo(supportFragmentManager, colorPickerFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FRAGMENT_COLOR_PICKER_TITLE, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
}

private fun CanvasActivity.filterSelectedColor(color: Int, ratio: Float) {
    if (isPrimaryColorSelected) setPixelColor(ColorUtils.blendARGB(getSelectedColor(), color, ratio))
    else setPixelColor(ColorUtils.blendARGB(getSelectedColor(), color, ratio))
}

private fun CanvasActivity.darkenSelectedColor() = filterSelectedColor(Color.BLACK, 0.2f)
private fun CanvasActivity.lightenSelectedColor() = filterSelectedColor(Color.WHITE, 0.2f)

private fun CanvasActivity.clearCanvas() {
    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) {
        pixel.pixelColor = null
    }

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)
}

fun CanvasActivity.extendedSetOnClickListeners() {
    binding.activityCanvasDoneButton.setOnClickListener {
        if (index == -1) {
            CoroutineScope(Dispatchers.IO).launch {
                AppData.db.pixelArtCreationsDao().insertPixelArt(PixelArt(BitmapConverter.convertBitmapToString(binding.activityCanvasCanvasFragmentHost.drawToBitmap()), binding.activityCanvasCanvasTitleEditText.text.toString(), JsonConverter.convertPixelListToJsonString(canvasFragmentInstance.myCanvasViewInstance.saveData()), false))
            }
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
        darkenSelectedColor()
        currentTool = Tools.DARKEN_TOOL
    }

    binding.activityCanvasLightenButton.setOnClickListener {
        lightenSelectedColor()
        currentTool = Tools.LIGHTEN_TOOL
    }

    binding.activityCanvasColorPickerButton.setOnClickListener {
        currentTool = Tools.COLOR_PICKER_TOOL
    }

    binding.activityCanvasResetCanvasButton.setOnClickListener {
        showDialog(
            "Clear canvas",
            "Are you sure you want to clear the canvas? This cannot be undone.",
            StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
            { _, _ ->
                clearCanvas()
            }, StringConstants.DIALOG_NEGATIVE_BUTTON_TEXT, { _, _ -> }, null)
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
        navigateTo(supportFragmentManager, findAndReplaceFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FRAGMENT_FIND_AND_REPLACE_TITLE, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
    }

    binding.activityCanvasEraseButton.setOnClickListener {
        currentTool = Tools.ERASE_TOOL
    }

    binding.activityCanvasUndoButton.setOnClickListener {
        if (canvasStates.size > 1) {
            canvasStates.remove(canvasStates.last())
            canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(canvasStates.last())
        } else {
            clearCanvas()
            canvasStates.clear()
        }
    }

}