package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import com.therealbluepandabear.pixapencil.utility.constants.Flags
import com.therealbluepandabear.pixapencil.utility.InternalBitmapFileNameGenerator
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    val fileHelperUtil = FileHelperUtilities.createInstance(this)
    val bmp = pixelGridViewInstance.pixelGridViewBitmap
    val fileName = InternalBitmapFileNameGenerator.generate(projectTitle!!)
    fileHelperUtil.storeBitmapToInternalStorage(fileName, bmp, Bitmap.CompressFormat.PNG) // Compress format MUST be PNG to show transparency

    outState.putString(StringConstants.Identifiers.PrevBitmapFilePathBundleIdentifier, fileName)
    outState.putInt(StringConstants.Identifiers.PrevOrientationBundleIdentifier, resources.configuration.orientation)
    outState.putInt(StringConstants.Identifiers.PrevPrimaryColorBundleIdentifier, (binding.activityCanvasColorPrimaryView.background as ColorDrawable).color)
    outState.putInt(StringConstants.Identifiers.PrevSecondaryColorBundleIdentifier, (binding.activityCanvasColorSecondaryView.background as ColorDrawable).color)
    outState.putString(StringConstants.Identifiers.PrevToolBundleIdentifier, currentTool.toolName)
    pixelGridViewInstance.currentBrush?.brushId?.let {
        outState.putInt(StringConstants.Identifiers.PrevBrushBundleIdentifier,
            it
        )
    }
    outState.putInt(StringConstants.Identifiers.PrevTabBundleIdentifier, currentTab)
    outState.putString(StringConstants.Identifiers.PrevSymmetryModeBundleIdentifier, pixelGridViewInstance.symmetryMode.symmetryName)
    outState.putInt(StringConstants.Identifiers.PrevRotationBundleIdentifier, outerCanvasInstance.getCurrentRotation().toInt())
}