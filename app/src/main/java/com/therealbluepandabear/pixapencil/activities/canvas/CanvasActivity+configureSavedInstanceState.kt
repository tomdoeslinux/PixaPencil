package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Build
import android.os.Bundle
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        if (Build.VERSION.SDK_INT < 26) {
            supportFragmentManager.apply {
                for (fragment in this.fragments) {
                    beginTransaction().remove(fragment).commit()
                }
            }
        }

        prevOrientation = savedInstanceState.getInt(StringConstants.Identifiers.PrevOrientationBundleIdentifier)
        prevBitmapFilePathStr = savedInstanceState.getString(StringConstants.Identifiers.PrevBitmapFilePathBundleIdentifier)
        prevPrimaryColor = savedInstanceState.getInt(StringConstants.Identifiers.PrevPrimaryColorBundleIdentifier)
        prevSecondaryColor = savedInstanceState.getInt(StringConstants.Identifiers.PrevSecondaryColorBundleIdentifier)
        prevToolStr = savedInstanceState.getString(StringConstants.Identifiers.PrevToolBundleIdentifier)
        prevBrushStr = savedInstanceState.getInt(StringConstants.Identifiers.PrevBrushBundleIdentifier)
        prevTab = savedInstanceState.getInt(StringConstants.Identifiers.PrevTabBundleIdentifier)
        prevUndoToolbarButtonDisabledEnabledState = viewModel.bitmapActionData.isNotEmpty() == true
        prevRedoToolbarButtonDisabledEnabledState = viewModel.undoStack.isNotEmpty() == true
        prevSymmetryModeStr = savedInstanceState.getString(StringConstants.Identifiers.PrevSymmetryModeBundleIdentifier)
        prevRotation = savedInstanceState.getInt(StringConstants.Identifiers.PrevRotationBundleIdentifier)
    }
}