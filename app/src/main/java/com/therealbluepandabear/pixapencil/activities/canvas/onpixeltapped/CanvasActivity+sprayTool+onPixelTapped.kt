package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.primaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.activities.canvas.sprayAlgorithmInstance
import com.therealbluepandabear.pixapencil.activities.canvas.sprayAlgorithmInstanceInitialized
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun sprayToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!sprayAlgorithmInstanceInitialized) {
        val sprayRadiusSharedPreference = sharedPreferenceObject.getInt(StringConstants.Identifiers.SharedPreferencesSprayRadiusIdentifier, IntConstants.SprayRadius)
        val sprayStrengthSharedPreference = sharedPreferenceObject.getInt(StringConstants.Identifiers.SharedPreferencesSprayStrengthIdentifier, IntConstants.SprayStrength)

        val s1 = SprayAlgorithm(primaryAlgorithmInfoParameter, sprayRadiusSharedPreference, sprayStrengthSharedPreference)
        sprayAlgorithmInstance = s1
    }

    sprayAlgorithmInstance.compute(coordinatesTapped)
}