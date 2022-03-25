package com.therealbluepandabear.pyxlmoose.utility

import android.view.HapticFeedbackConstants
import android.view.View

object HapticFeedbackWrapper {
    fun performHapticFeedback(view: View) {
        view.performHapticFeedback(
            HapticFeedbackConstants.VIRTUAL_KEY,
            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        )
    }
}