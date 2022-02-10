package com.realtomjoney.pyxlmoose.fragments.newcanvas

import android.view.HapticFeedbackConstants
import android.view.View
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun performHapticFeedbackOn(view: View) {
    view.performHapticFeedback(
        HapticFeedbackConstants.VIRTUAL_KEY,
        HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
    )
}

fun setOnClickListeners() {
    binding.apply {
        fragmentNewCanvasDoneButton.setOnClickListener {
            val parsedSpanCountValue =
                Integer.parseInt(fragmentNewCanvasSpanCountTextInputEditText.text.toString())

            if (parsedSpanCountValue !in IntConstants.SPAN_COUNT_MIN..IntConstants.SPAN_COUNT_MAX) {
                performHapticFeedbackOn(binding.fragmentNewCanvasDoneButton)
                root.showSnackbar(
                    StringConstants.EX_INVALID_SPAN_COUNT,
                    SnackbarDuration.DEFAULT
                )
            } else {
                try {
                    caller.onDoneButtonPressed(
                        Integer.parseInt(fragmentNewCanvasSpanCountTextInputEditText.text.toString()),
                        fragmentNewCanvasSpanCountTextInputEditText,
                        fragmentNewCanvasProjectTitleTextInputEditText
                    )
                } catch (ex: Exception) {
                    performHapticFeedbackOn(binding.fragmentNewCanvasDoneButton)
                    root.showSnackbar(
                        StringConstants.EX_INVALID_SPAN_COUNT,
                        SnackbarDuration.DEFAULT
                    )
                }
            }
        }
    }
}