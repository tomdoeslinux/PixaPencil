package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.sprayToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!sprayAlgorithmInstanceInitialized) {
        val sprayRadiusSharedPreference = sharedPreferenceObject.getInt(StringConstants.Identifiers.SharedPreferencesSprayRadiusIdentifier, IntConstants.SprayRadius)
        val sprayStrengthSharedPreference = sharedPreferenceObject.getInt(StringConstants.Identifiers.SharedPreferencesSprayStrengthIdentifier, IntConstants.SprayStrength)

        val s1 = SprayAlgorithm(primaryAlgorithmInfoParameter, sprayRadiusSharedPreference, sprayStrengthSharedPreference)
        sprayAlgorithmInstance = s1
    }

    sprayAlgorithmInstance.compute(coordinatesTapped)
}