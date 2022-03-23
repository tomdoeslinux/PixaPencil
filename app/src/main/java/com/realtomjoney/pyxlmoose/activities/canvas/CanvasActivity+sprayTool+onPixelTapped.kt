package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.SprayAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun sprayToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!sprayAlgorithmInstanceInitialized) {
        val sprayRadiusSharedPreference = sharedPreferenceObject.getInt(StringConstants.SharedPreferencesSprayRadiusIdentifier, IntConstants.SprayRadius)
        val sprayStrengthSharedPreference = sharedPreferenceObject.getInt(StringConstants.SharedPreferencesSprayStrengthIdentifier, IntConstants.SprayStrength)

        val s1 = SprayAlgorithm(primaryAlgorithmInfoParameter, sprayRadiusSharedPreference, sprayStrengthSharedPreference)
        sprayAlgorithmInstance = s1
    }

    sprayAlgorithmInstance.compute(coordinatesTapped)
}