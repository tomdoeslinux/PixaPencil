package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.extendedOnResume() {
    if (prevOrientation != 0) {
        if (prevOrientation != resources.configuration.orientation) {
            onOrientationChanged()
            prevOrientation = resources.configuration.orientation
        }
    }

    if (prevBitmapStr != null && prevOrientation != 0) {
        Handler(Looper.getMainLooper()).postDelayed({
            val convertedBMP = BitmapConverter.convertStringToBitmap(prevBitmapStr!!)
            if (convertedBMP != null) {
                pixelGridViewInstance.replaceBitmap(convertedBMP)
            }
        }, 1000)
    }

    if (prevPrimaryColor != null && prevSecondaryColor != null) {
        Handler().postDelayed({
            setPrimaryPixelColor(prevPrimaryColor!!)
            setSecondaryPixelColor(prevSecondaryColor!!)
        }, 1000)
    }

    if (prevTool != null) {
        Handler().postDelayed({
            currentTool = if (Tools.findToolByName(prevTool!!) != null) {
                Tools.findToolByName(prevTool!!)!!
            } else {
                Tools.PencilTool
            }

            toolsFragmentInstance?.tapOnToolByName(prevTool!!)
        }, 1000)
    }

    if (prevBrush != null) {
        Handler().postDelayed({
            pixelGridViewInstance.currentBrush = BrushesDatabase.toList().find { it.brushName == prevBrush }
        }, 1000)
    }

    if (prevTab != 0) {
        Handler().postDelayed({
            binding.activityCanvasTabLayout.getTabAt(prevTab)?.select()
        }, 1000)
    }

    if (prevUndoToolbarButtonDisabledEnabledState) {
        Handler().postDelayed({
            menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
        }, 1000)
    }

    if (prevRedoToolbarButtonDisabledEnabledState) {
        Handler().postDelayed({
            menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
        }, 1000)
    }

    if (prevUndoStackJsonStr != null) {
        Handler().postDelayed({
            pixelGridViewInstance.bitmapActionData = JsonConverter.convertJsonStringToListOfBitmapAction(
                prevUndoStackJsonStr!!
            ).toMutableList()
        }, 1000)
    }

    if (prevRedoStackJsonStr != null) {
        Handler().postDelayed({
            pixelGridViewInstance.undoStack = JsonConverter.convertJsonStringToListOfBitmapAction(
                prevRedoStackJsonStr!!
            ).toMutableList()
        }, 1000)
    }
}