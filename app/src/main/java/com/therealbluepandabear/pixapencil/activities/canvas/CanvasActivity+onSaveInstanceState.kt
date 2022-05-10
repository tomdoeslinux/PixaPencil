package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.FileHelperUtilities
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.InternalBitmapFileNameGenerator
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    if (!Flags.PressedBackFromImg && !Flags.PressedBackToExit) {
        val fileHelperUtil = FileHelperUtilities.createInstance(this, outerCanvasInstance, null)
        val bmp = pixelGridViewInstance.pixelGridViewBitmap
        val fileName = InternalBitmapFileNameGenerator.generate(projectTitle!!)
        fileHelperUtil.storeBitmapToInternalStorage(fileName, bmp, Bitmap.CompressFormat.PNG) // Compress format MUST be PNG to show transparency

        outState.putString(StringConstants.Identifiers.prevBitmapFilePathStrBundleIdentifier, fileName)
        outState.putInt(StringConstants.Identifiers.prevOrientationBundleIdentifier, resources.configuration.orientation)
        outState.putInt(StringConstants.Identifiers.prevPrimaryColorBundleIdentifier, (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
        outState.putInt(StringConstants.Identifiers.prevSecondaryColorBundleIdentifier, (binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
        outState.putString(StringConstants.Identifiers.prevToolBundleIdentifier, currentTool.toolName)
        outState.putString(StringConstants.Identifiers.prevBrushBundleIdentifier, pixelGridViewInstance.currentBrush?.brushName)
        outState.putInt(StringConstants.Identifiers.prevTabBundleIdentifier, currentTab)
        outState.putString(StringConstants.Identifiers.prevUndoStackJsonStrIdentifier, JsonConverter.convertListToJsonString(pixelGridViewInstance.bitmapActionData))
        outState.putString(StringConstants.Identifiers.prevRedoStackJsonStrIdentifier, JsonConverter.convertListToJsonString(pixelGridViewInstance.undoStack))
        outState.putString(StringConstants.Identifiers.prevSymmetryModeStrIdentifier, pixelGridViewInstance.symmetryMode.symmetryName)
        outState.putInt(StringConstants.Identifiers.prevRotationStrIdentifier, outerCanvasInstance.getCurrentRotation().toInt())
    } else {
        Flags.PressedBackFromImg = false
    }
}