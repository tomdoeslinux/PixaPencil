package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    if (!Flags.PressedBackFromImg && !Flags.PressedBackToExit) {
        outState.putInt(
            StringConstants.Identifiers.prevOrientationBundleIdentifier,
            resources.configuration.orientation
        )
        outState.putString(
            StringConstants.Identifiers.prevBitmapStrBundleIdentifier,
            BitmapConverter.convertBitmapToString(pixelGridViewInstance.pixelGridViewBitmap)
        )
        outState.putInt(
            StringConstants.Identifiers.prevPrimaryColorBundleIdentifier,
            (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color
        )
        outState.putInt(
            StringConstants.Identifiers.prevSecondaryColorBundleIdentifier,
            (binding.activityCanvasColorSecondaryView.background as ColorDrawable).color
        )
        outState.putString(StringConstants.Identifiers.prevToolBundleIdentifier, currentTool.toolName)
        outState.putString(
            StringConstants.Identifiers.prevBrushBundleIdentifier,
            pixelGridViewInstance.currentBrush?.brushName
        )
        outState.putInt(StringConstants.Identifiers.prevTabBundleIdentifier, currentTab)
        outState.putString(
            StringConstants.Identifiers.prevUndoStackJsonStrIdentifier,
            JsonConverter.convertListToJsonString(pixelGridViewInstance.bitmapActionData)
        )
        outState.putString(
            StringConstants.Identifiers.prevRedoStackJsonStrIdentifier,
            JsonConverter.convertListToJsonString(pixelGridViewInstance.undoStack)
        )
        outState.putString(
            StringConstants.Identifiers.prevSymmetryModeStrIdentifier,
            pixelGridViewInstance.symmetryMode?.symmetryName,
        )
    } else {
        Flags.PressedBackFromImg = false
    }
}