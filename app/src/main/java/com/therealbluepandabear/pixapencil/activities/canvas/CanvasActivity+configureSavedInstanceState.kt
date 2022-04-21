package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.Identifiers.prevOrientationBundleIdentifier)
        prevBitmapStr = savedInstanceState.getString(StringConstants.Identifiers.prevBitmapStrBundleIdentifier)
        prevPrimaryColor = savedInstanceState.getInt(StringConstants.Identifiers.prevPrimaryColorBundleIdentifier)
        prevSecondaryColor = savedInstanceState.getInt(StringConstants.Identifiers.prevSecondaryColorBundleIdentifier)
        prevToolStr = savedInstanceState.getString(StringConstants.Identifiers.prevToolBundleIdentifier)
        prevBrushStr = savedInstanceState.getString(StringConstants.Identifiers.prevBrushBundleIdentifier)
        prevTab = savedInstanceState.getInt(StringConstants.Identifiers.prevTabBundleIdentifier)
        prevUndoStackJsonStr = savedInstanceState.getString(StringConstants.Identifiers.prevUndoStackJsonStrIdentifier)
        prevRedoStackJsonStr = savedInstanceState.getString(StringConstants.Identifiers.prevRedoStackJsonStrIdentifier)
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
        prevSymmetryModeStr = savedInstanceState.getString(StringConstants.Identifiers.prevSymmetryModeStrIdentifier)
    }
}