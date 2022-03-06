package com.realtomjoney.pyxlmoose.fragments.newcanvas

import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
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

            if (widthValue !in IntConstants.SPAN_COUNT_MIN..IntConstants.SPAN_COUNT_MAX
                &&
                heightValue !in IntConstants.SPAN_COUNT_MIN..IntConstants.SPAN_COUNT_MAX ) {
                HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
                root.showSnackbar(
                    StringConstants.EX_INVALID_WIDTH_HEIGHT,
                    SnackbarDuration.DEFAULT
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
                        StringConstants.EX_INVALID_WIDTH_HEIGHT,
                        SnackbarDuration.DEFAULT
                    )
                }
            }
        }
    }
}