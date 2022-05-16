package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.Identifiers.prevOrientationBundleIdentifier)
        prevBitmapFilePathStr = savedInstanceState.getString(StringConstants.Identifiers.prevBitmapFilePathStrBundleIdentifier)
        prevPrimaryColor = savedInstanceState.getInt(StringConstants.Identifiers.prevPrimaryColorBundleIdentifier)
        prevSecondaryColor = savedInstanceState.getInt(StringConstants.Identifiers.prevSecondaryColorBundleIdentifier)
        prevToolStr = savedInstanceState.getString(StringConstants.Identifiers.prevToolBundleIdentifier)
        prevBrushStr = savedInstanceState.getString(StringConstants.Identifiers.prevBrushBundleIdentifier)
        prevTab = savedInstanceState.getInt(StringConstants.Identifiers.prevTabBundleIdentifier)
        prevUndoToolbarButtonDisabledEnabledState = viewModel.bitmapActionData.isNotEmpty() == true
        prevRedoToolbarButtonDisabledEnabledState = viewModel.undoStack.isNotEmpty() == true
        prevSymmetryModeStr = savedInstanceState.getString(StringConstants.Identifiers.prevSymmetryModeStrIdentifier)
        prevRotation = savedInstanceState.getInt(StringConstants.Identifiers.prevRotationStrIdentifier)
    }
}