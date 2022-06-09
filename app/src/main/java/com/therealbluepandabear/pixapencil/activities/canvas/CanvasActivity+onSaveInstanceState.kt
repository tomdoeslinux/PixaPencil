package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.os.Bundle
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.InternalBitmapFileNameGenerator
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    val fileHelperUtil = FileHelperUtilities.createInstance(this)
    val bmp = pixelGridViewInstance.pixelGridViewBitmap
    val fileName = InternalBitmapFileNameGenerator.generate(projectTitle!!)
    fileHelperUtil.storeBitmapToInternalStorage(fileName, bmp, Bitmap.CompressFormat.PNG) // Compress format MUST be PNG to show transparency

    outState.putString(StringConstants.Identifiers.PrevBitmapFilePathBundleIdentifier, fileName)
    outState.putInt(StringConstants.Identifiers.PrevOrientationBundleIdentifier, resources.configuration.orientation)
    outState.putString(StringConstants.Identifiers.PrevToolBundleIdentifier, currentTool.toolName)
    outState.putInt(StringConstants.Identifiers.PrevTabBundleIdentifier, currentTab)
    outState.putInt(StringConstants.Identifiers.PrevRotationBundleIdentifier, outerCanvasInstance.getCurrentRotation().toInt())
}