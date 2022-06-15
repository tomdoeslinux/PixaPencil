package com.therealbluepandabear.pixapencil.fragments.newproject

import android.widget.FrameLayout
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.checkbox.MaterialCheckBox
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

private var invalidTitle = false
private var invalidWidth = false
private var invalidHeight = false

private fun NewProjectFragment.checkForTitleError() {
    val title = binding.fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

    if (title.isBlank()) {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = getString(R.string.exception_invalid_project_name_in_code_str)
        invalidTitle = true
    } else {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = null
        invalidTitle = false
    }
}

private fun NewProjectFragment.checkForWidthError() {
    when (binding.fragmentNewCanvasWidthTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_empty_width_in_code_str)
            invalidWidth = true
        }

        !in IntConstants.WidthHeightMin..IntConstants.WidthHeightMax -> {
            binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_invalid_width_in_code_str)
            invalidWidth = true
        }

        else -> {
            binding.fragmentNewCanvasWidthTextInputLayout.error = null
            invalidWidth = false
        }
    }
}

private fun NewProjectFragment.checkForHeightError() {
    when (binding.fragmentNewCanvasHeightTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            binding.fragmentNewCanvasHeightTextInputLayout.error = getString(R.string.exception_empty_height_in_code_str)
            invalidHeight = true
        }

        !in IntConstants.WidthHeightMin..IntConstants.WidthHeightMax -> {
            binding.fragmentNewCanvasHeightTextInputLayout.error = getString(R.string.exception_invalid_height_in_code_str)
            invalidHeight = true
        }

        else -> {
            binding.fragmentNewCanvasHeightTextInputLayout.error = null
            invalidHeight = false
        }
    }
}

fun NewProjectFragment.setOnClickListeners() {
    binding.root.post {
        binding.fragmentNewCanvasProjectTitleTextInputEditText.doAfterTextChanged {
            checkForTitleError()
        }

        binding.fragmentNewCanvasWidthTextInputEditText.doAfterTextChanged {
            checkForWidthError()
        }

        binding.fragmentNewCanvasHeightTextInputEditText.doAfterTextChanged {
            checkForHeightError()
        }

        binding.fragmentNewCanvasDoneButton.setOnClickListener {
            checkForTitleError()
            checkForWidthError()
            checkForHeightError()

            if (!invalidTitle && !invalidWidth && !invalidHeight) {
                try {
                    val title =
                        binding.fragmentNewCanvasProjectTitleTextInputEditText.text.toString()
                    val widthValue: Int =
                        binding.fragmentNewCanvasWidthTextInputEditText.text.toString().toInt()
                    val heightValue: Int =
                        binding.fragmentNewCanvasHeightTextInputEditText.text.toString().toInt()

                    if (widthValue + heightValue >= 2000 && (requireActivity() as MainActivity).showLargeCanvasSizeWarning) {
                        val frameLayout: FrameLayout =
                            this@setOnClickListeners.activity?.layoutInflater?.inflate(
                                R.layout.dont_show_large_canvas_warning_again_checkbox,
                                requireView().findViewById(android.R.id.content),
                                false
                            )
                                    as FrameLayout
                        val checkBox = frameLayout.getChildAt(0) as MaterialCheckBox

                        requireActivity().showDialog(
                            getString(R.string.generic_warning_in_code_str),
                            getString(R.string.dialog_large_canvas_warning_text_in_code_str),
                            getString(R.string.dialog_large_canvas_warning_positive_button_text_in_code_str),
                            { _, _ ->
                                if (checkBox.isChecked) {
                                    (requireActivity() as MainActivity).showLargeCanvasSizeWarning =
                                        false

                                    with((requireActivity() as MainActivity).sharedPreferenceObject.edit()) {
                                        putBoolean(
                                            StringConstants.Identifiers.SharedPreferenceShowLargeCanvasSizeWarningIdentifier,
                                            (requireActivity() as MainActivity).showLargeCanvasSizeWarning
                                        )
                                        apply()
                                    }
                                }

                                caller.onDoneButtonPressed(
                                    title,
                                    widthValue,
                                    heightValue,
                                    paramSpotLightInProgress
                                )
                            },
                            getString(R.string.dialog_unsaved_changes_negative_button_text_in_code_str),
                            { _, _ ->
                            },
                            frameLayout
                        )
                    } else {
                        caller.onDoneButtonPressed(
                            title,
                            widthValue,
                            heightValue,
                            paramSpotLightInProgress
                        )
                    }
                } catch (exception: Exception) {
                    HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
                }
            } else {
                HapticFeedbackWrapper.performHapticFeedback(binding.fragmentNewCanvasDoneButton)
            }
        }
    }
}