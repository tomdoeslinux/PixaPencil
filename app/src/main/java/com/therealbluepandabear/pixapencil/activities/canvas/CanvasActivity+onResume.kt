package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
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

    if (prevPrimaryColor != null && prevSecondaryColor != null && prevOrientation != 0) {
        Handler().postDelayed({
            setPrimaryPixelColor(prevPrimaryColor!!)
            setSecondaryPixelColor(prevSecondaryColor!!)
        }, 1000)
    }

    if (prevToolStr != null && prevOrientation != 0) {
        Handler().postDelayed({
            currentTool = if (Tools.findToolByName(prevToolStr!!) != null) {
                Tools.findToolByName(prevToolStr!!)!!
            } else {
                Tools.PencilTool
            }

            toolsFragmentInstance?.tapOnToolByName(prevToolStr!!)
        }, 1000)
    }

    if (prevBrushStr != null && prevOrientation != 0) {
        Handler().postDelayed({
            pixelGridViewInstance.currentBrush = BrushesDatabase.toList().find { it.brushName == prevBrushStr }
        }, 1000)
    }

    if (prevTab != 0 && prevOrientation != 0) {
        Handler().postDelayed({
            binding.activityCanvasTabLayout.getTabAt(prevTab)?.select()
        }, 1000)
    }

    if (prevUndoToolbarButtonDisabledEnabledState && prevOrientation != 0) {
        Handler().postDelayed({
            menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
        }, 1000)
    }

    if (prevRedoToolbarButtonDisabledEnabledState && prevOrientation != 0) {
        Handler().postDelayed({
            menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
        }, 1000)
    }

    if (prevUndoStackJsonStr != null && prevOrientation != 0) {
        Handler().postDelayed({
            pixelGridViewInstance.bitmapActionData = JsonConverter.convertJsonStringToListOfBitmapAction(
                prevUndoStackJsonStr!!
            ).toMutableList()
        }, 1000)
    }

    if (prevRedoStackJsonStr != null && prevOrientation != 0) {
        Handler().postDelayed({
            pixelGridViewInstance.undoStack = JsonConverter.convertJsonStringToListOfBitmapAction(
                prevRedoStackJsonStr!!
            ).toMutableList()
        }, 1000)
    }

    if (prevSymmetryModeStr != null && prevOrientation != 0) {
        Handler().postDelayed({
            pixelGridViewInstance.symmetryMode = SymmetryMode.values().first { it.symmetryName == prevSymmetryModeStr }

            when (pixelGridViewInstance.symmetryMode) {
                SymmetryMode.Horizontal -> {
                    menu.findItem(R.id.appMenu_symmetry_horizontal_subItem).isChecked = true
                }

                SymmetryMode.Vertical -> {
                    menu.findItem(R.id.appMenu_symmetry_vertical_subItem).isChecked = true
                }

                SymmetryMode.Quad -> {
                    menu.findItem(R.id.appMenu_symmetry_quad_subItem).isChecked = true
                }

                else -> { }
            }
        }, 1000)
    }
}