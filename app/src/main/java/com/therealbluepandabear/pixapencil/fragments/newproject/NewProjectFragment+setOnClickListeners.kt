package com.therealbluepandabear.pixapencil.fragments.newproject

import androidx.core.widget.doAfterTextChanged
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.IntConstants

var invalidValues = false

private fun NewProjectFragment.checkForTitleError() {
    val title = binding.fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

    if (title.isBlank()) {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = getString(R.string.exception_invalid_title_in_code_str)
        invalidValues = true
    } else {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = null
        invalidValues = false
    }
}

private fun NewProjectFragment.checkForWidthError() {
    val widthValue: Int = binding.fragmentNewCanvasWidthTextInputEditText.text.toString().toIntOrNull() ?: 0

    if (widthValue !in IntConstants.SpanCountMin..IntConstants.SpanCountMax) {
        binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_invalid_width_in_code_str)
        invalidValues = true
    } else {
        binding.fragmentNewCanvasWidthTextInputLayout.error = null
        invalidValues = false
    }
}

private fun NewProjectFragment.checkForHeightError() {
    val heightValue: Int = binding.fragmentNewCanvasHeightTextInputEditText.text.toString().toIntOrNull() ?: 0

    if (heightValue !in IntConstants.SpanCountMin..IntConstants.SpanCountMax) {
        binding.fragmentNewCanvasHeightTextInputLayout.error = getString(R.string.exception_invalid_height_in_code_str)
        invalidValues = true
    } else {
        binding.fragmentNewCanvasHeightTextInputLayout.error = null
        invalidValues = false
    }
}

fun NewProjectFragment.setOnClickListeners() {
    binding.apply {
        fragmentNewCanvasProjectTitleTextInputEditText.doAfterTextChanged {
            checkForTitleError()
        }

        fragmentNewCanvasWidthTextInputEditText.doAfterTextChanged {
            checkForWidthError()
        }

        fragmentNewCanvasHeightTextInputEditText.doAfterTextChanged {
            checkForHeightError()
        }

        fragmentNewCanvasDoneButton.setOnClickListener {
            checkForTitleError()
            checkForWidthError()
            checkForHeightError()

            if (!invalidValues) {
                try {
                    val title = fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

                    val widthValue: Int = binding.fragmentNewCanvasWidthTextInputEditText.text.toString().toInt()
                    val heightValue: Int = binding.fragmentNewCanvasHeightTextInputEditText.text.toString().toInt()

                    caller.onDoneButtonPressed(
                        title,
                        widthValue,
                        heightValue
                    )
                } catch (exception: Exception) {
                    HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
                }
            }
        }
    }
}