package com.therealbluepandabear.pixapencil.fragments.newproject

import androidx.core.widget.doAfterTextChanged
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.IntConstants

var invalidTitle = false
var invalidWidth = false
var invalidHeight = false

private fun NewProjectFragment.checkForTitleError() {
    val title = binding.fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

    if (title.isBlank()) {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = getString(R.string.exception_invalid_title_in_code_str)
        invalidTitle = true
    } else {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = null
        invalidTitle = false
    }
}

private fun NewProjectFragment.checkForWidthError() {
    val widthValue: Int = binding.fragmentNewCanvasWidthTextInputEditText.text.toString().toIntOrNull() ?: 0

    if (widthValue !in IntConstants.WidthHeightMin..IntConstants.WidthHeightMax) {
        binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_invalid_width_in_code_str)
        invalidWidth = true
    } else {
        binding.fragmentNewCanvasWidthTextInputLayout.error = null
        invalidWidth = false
    }
}

private fun NewProjectFragment.checkForHeightError() {
    val heightValue: Int = binding.fragmentNewCanvasHeightTextInputEditText.text.toString().toIntOrNull() ?: 0

    if (heightValue !in IntConstants.WidthHeightMin..IntConstants.WidthHeightMax) {
        binding.fragmentNewCanvasHeightTextInputLayout.error = getString(R.string.exception_invalid_height_in_code_str)
        invalidHeight = true
    } else {
        binding.fragmentNewCanvasHeightTextInputLayout.error = null
        invalidHeight = false
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

            if (!invalidTitle && !invalidWidth && !invalidHeight) {
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
            } else {
                HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
            }
        }
    }
}