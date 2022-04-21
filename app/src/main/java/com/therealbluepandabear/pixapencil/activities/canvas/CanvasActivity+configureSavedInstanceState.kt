package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.prevOrientationBundleIdentifier)
        prevBitmapStr = savedInstanceState.getString(StringConstants.prevBitmapStrBundleIdentifier)
        prevPrimaryColor = savedInstanceState.getInt(StringConstants.prevPrimaryColorBundleIdentifier)
        prevSecondaryColor = savedInstanceState.getInt(StringConstants.prevSecondaryColorBundleIdentifier)
        prevToolStr = savedInstanceState.getString(StringConstants.prevToolBundleIdentifier)
        prevBrushStr = savedInstanceState.getString(StringConstants.prevBrushBundleIdentifier)
        prevTab = savedInstanceState.getInt(StringConstants.prevTabBundleIdentifier)
        prevUndoStackJsonStr = savedInstanceState.getString(StringConstants.prevUndoStackJsonStrIdentifier)
        prevRedoStackJsonStr = savedInstanceState.getString(StringConstants.prevRedoStackJsonStrIdentifier)
        prevUndoToolbarButtonDisabledEnabledState = prevUndoStackJsonStr?.let {
            JsonConverter.convertJsonStringToListOfBitmapAction(
                it
            ).isNotEmpty()
        } == true
        prevRedoToolbarButtonDisabledEnabledState = prevUndoStackJsonStr?.let {
            JsonConverter.convertJsonStringToListOfBitmapAction(
                it
            ).isNotEmpty()
        } == true
        prevSymmetryModeStr = savedInstanceState.getString(StringConstants.prevSymmetryModeStrIdentifier)
    }
}