package com.realtomjoney.pyxlmoose.fragments.newcanvas

import com.realtomjoney.pyxlmoose.enums.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.utility.HapticFeedbackWrapper
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun setOnClickListeners() {
    binding.apply {
        fragmentNewCanvasDoneButton.setOnClickListener {
            val heightValue =
                Integer.parseInt(fragmentNewCanvasHeightTextInputEditText.text.toString())
            val widthValue =
                Integer.parseInt(fragmentNewCanvasWidthTextInputEditText.text.toString())
            val title = fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

            if (widthValue !in IntConstants.SpanCountMin..IntConstants.SpanCountMax
                &&
                heightValue !in IntConstants.SpanCountMin..IntConstants.SpanCountMax ) {
                HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
                root.showSnackbar(
                    StringConstants.ExceptionInvalidWidthHeightMessage,
                    SnackbarDuration.Default
                )
            } else {
                try {
                    caller.onDoneButtonPressed(
                        title,
                        widthValue,
                        heightValue
                    )
                } catch (ex: Exception) {
                    HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
                    root.showSnackbar(
                        StringConstants.ExceptionInvalidWidthHeightMessage,
                        SnackbarDuration.Default
                    )
                }
            }
        }
    }
}