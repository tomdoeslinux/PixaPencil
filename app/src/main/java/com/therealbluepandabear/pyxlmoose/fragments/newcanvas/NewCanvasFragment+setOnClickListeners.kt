package com.therealbluepandabear.pyxlmoose.fragments.newcanvas

import com.therealbluepandabear.pyxlmoose.enums.SnackbarDuration
import com.therealbluepandabear.pyxlmoose.extensions.showSnackbar
import com.therealbluepandabear.pyxlmoose.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pyxlmoose.utility.IntConstants
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

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