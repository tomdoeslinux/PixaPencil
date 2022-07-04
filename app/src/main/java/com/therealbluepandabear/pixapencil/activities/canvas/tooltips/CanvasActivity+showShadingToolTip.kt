package com.therealbluepandabear.pixapencil.activities.canvas.tooltips

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowShadingToolTipValueFromPreference
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithActionAndCallback
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.showShadingToolTip() {
    binding.clayout?.showSnackbarWithActionAndCallback(
        getString(R.string.shading_tool_tip_in_code_str),
        SnackbarDuration.Medium,
        getString(R.string.tool_tip_dont_show_again_in_code_str),
        {
            with(sharedPreferenceObject.edit()) {
                putBoolean(
                    StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SHADING_TOOLTIP_IDENTIFIER,
                    false
                )
                apply()
            }
            applyShowShadingToolTipValueFromPreference()
        }
    ) {
        with(sharedPreferenceObject.edit()) {
            putBoolean(
                StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SHADING_TOOLTIP_IDENTIFIER,
                false
            )
            apply()
        }
        applyShowShadingToolTipValueFromPreference()
    }
}