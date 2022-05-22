package com.therealbluepandabear.pixapencil.activities.canvas.tooltips

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowSprayToolTipValueFromPreference
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.showSprayToolTip() {
    binding.root.showSnackbarWithAction(
        getString(R.string.spray_tool_tip_in_code_str),
        SnackbarDuration.Medium,
        getString(R.string.tool_tip_dont_show_again_in_code_str)
    ) {
        with(sharedPreferenceObject.edit()) {
            putBoolean(
                StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier,
                false
            )
            apply()
        }
        applyShowSprayToolTipValueFromPreference()
    }
}