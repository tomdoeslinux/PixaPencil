package com.therealbluepandabear.pixapencil.activities.canvas.tooltips

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowSprayToolTipValueFromPreference
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithActionAndCallback
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.showSprayToolTip() {
    binding.activityCanvasCoordinatorLayout.showSnackbarWithActionAndCallback(
        getString(R.string.spray_tool_tip_in_code_str),
        SnackbarDuration.Medium,
        getString(R.string.tool_tip_dont_show_again_in_code_str),
        {
            with(sharedPreferenceObject.edit()) {
                putBoolean(
                    StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SPRAY_TOOLTIP_IDENTIFIER,
                    false
                )
                apply()
            }
            applyShowSprayToolTipValueFromPreference()
        }
    ) {
        with(sharedPreferenceObject.edit()) {
            putBoolean(
                StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SPRAY_TOOLTIP_IDENTIFIER,
                false
            )
            apply()
        }
        applyShowSprayToolTipValueFromPreference()
    }
}