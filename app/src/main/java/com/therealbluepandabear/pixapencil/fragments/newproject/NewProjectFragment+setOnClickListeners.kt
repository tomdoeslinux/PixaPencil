package com.therealbluepandabear.pixapencil.fragments.newproject

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.IntConstants

fun NewProjectFragment.setOnClickListeners() {
    binding.apply {
        fragmentNewCanvasDoneButton.setOnClickListener {
            val heightValue =
                Integer.parseInt(fragmentNewCanvasHeightTextInputEditText.text.toString())
            val widthValue =
                Integer.parseInt(fragmentNewCanvasWidthTextInputEditText.text.toString())
            val title = fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

            if (widthValue !in IntConstants.SpanCountMin..IntConstants.SpanCountMax
                ||
                heightValue !in IntConstants.SpanCountMin..IntConstants.SpanCountMax) {
                HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
                root.showSnackbar(
                    getString(R.string.exception_invalid_width_height_message_in_code_str),
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
                        getString(R.string.exception_invalid_width_height_message_in_code_str),
                        SnackbarDuration.Default
                    )
                }
            }
        }
    }
}