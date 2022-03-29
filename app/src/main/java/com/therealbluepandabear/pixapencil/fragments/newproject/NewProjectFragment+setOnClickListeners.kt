package com.therealbluepandabear.pixapencil.fragments.newproject

import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

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