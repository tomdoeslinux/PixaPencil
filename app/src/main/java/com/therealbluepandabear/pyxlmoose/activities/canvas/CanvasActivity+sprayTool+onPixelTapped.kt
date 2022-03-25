package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.SprayAlgorithm
import com.therealbluepandabear.pyxlmoose.models.Coordinates
import com.therealbluepandabear.pyxlmoose.utility.IntConstants
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun sprayToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!sprayAlgorithmInstanceInitialized) {
        val sprayRadiusSharedPreference = sharedPreferenceObject.getInt(StringConstants.SharedPreferencesSprayRadiusIdentifier, IntConstants.SprayRadius)
        val sprayStrengthSharedPreference = sharedPreferenceObject.getInt(StringConstants.SharedPreferencesSprayStrengthIdentifier, IntConstants.SprayStrength)

        val s1 = SprayAlgorithm(primaryAlgorithmInfoParameter, sprayRadiusSharedPreference, sprayStrengthSharedPreference)
        sprayAlgorithmInstance = s1
    }

    sprayAlgorithmInstance.compute(coordinatesTapped)
}