package com.therealbluepandabear.pixapencil.activities.canvas.tooltips

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowDitherToolTipFromPreference
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithActionAndCallback
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.showDitherToolTip() {
    binding.activityCanvasCoordinatorLayout.showSnackbarWithActionAndCallback(
        getString(R.string.tool_tip_dither),
        SnackbarDuration.Medium,
        getString(R.string.tool_tip_dont_show_again),
        {
            with(sharedPreferenceObject.edit()) {
                putBoolean(
                    StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_DITHER_TOOLTIP_IDENTIFIER,
                    false
                )
                apply()
            }
            applyShowDitherToolTipFromPreference()
        }
    ) {
        with(sharedPreferenceObject.edit()) {
            putBoolean(
                StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_DITHER_TOOLTIP_IDENTIFIER,
                false
            )
            apply()
        }
        applyShowDitherToolTipFromPreference()
    }
}