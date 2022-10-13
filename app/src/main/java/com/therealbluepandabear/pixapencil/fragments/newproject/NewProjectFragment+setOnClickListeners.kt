/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.fragments.newproject

import android.widget.FrameLayout
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.hideSoftInput
import com.therealbluepandabear.pixapencil.utility.HapticFeedbackWrapper
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

private var invalidTitle = false
private var invalidWidth = false
private var invalidHeight = false

private fun NewProjectFragment.checkForTitleError() {
    val title = binding.fragmentNewCanvasProjectTitleTextInputEditText.text.toString()

    if (title.isBlank()) {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.error = getString(R.string.exception_invalid_project_name)
        invalidTitle = true
    } else {
        binding.fragmentNewCanvasProjectTitleTextInputLayout.isErrorEnabled = false
        invalidTitle = false
    }
}

private fun NewProjectFragment.checkForWidthError() {
    when (binding.fragmentNewCanvasWidthTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            if (binding.fragmentNewCanvasWidthTextInputEditText.text.toString().isEmpty()) {
                binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_empty_width)
            } else {
                binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_invalid_width)
            }

            invalidWidth = true
        }

        !in IntConstants.WIDTH_HEIGHT_MIN..IntConstants.WIDTH_HEIGHT_MAX -> {
            binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_invalid_width)
            invalidWidth = true
        }

        else -> {
            binding.fragmentNewCanvasWidthTextInputLayout.isErrorEnabled = false
            invalidWidth = false
        }
    }
}

private fun NewProjectFragment.checkForHeightError() {
    when (binding.fragmentNewCanvasHeightTextInputEditText.text.toString().toIntOrNull()) {
        null -> {
            if (binding.fragmentNewCanvasHeightTextInputEditText.text.toString().isEmpty()) {
                binding.fragmentNewCanvasHeightTextInputLayout.error = getString(R.string.exception_empty_height)
            } else {
                binding.fragmentNewCanvasWidthTextInputLayout.error = getString(R.string.exception_invalid_width)
            }

            invalidHeight = true
        }

        !in IntConstants.WIDTH_HEIGHT_MIN..IntConstants.WIDTH_HEIGHT_MAX -> {
            binding.fragmentNewCanvasHeightTextInputLayout.error = getString(R.string.exception_invalid_height)
            invalidHeight = true
        }

        else -> {
            binding.fragmentNewCanvasHeightTextInputLayout.isErrorEnabled = false
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
                    binding.fragmentNewCanvasProjectTitleTextInputEditText.hideSoftInput()
                    binding.fragmentNewCanvasWidthTextInputEditText.hideSoftInput()
                    binding.fragmentNewCanvasHeightTextInputEditText.hideSoftInput()

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

                        val alertDialog = MaterialAlertDialogBuilder(this.requireContext(), R.style.ThemeOverlay_App_MaterialAlertDialog)
                            .setTitle(R.string.generic_warning)
                            .setView(frameLayout)
                            .setMessage(R.string.dialog_large_canvas_warning_text)
                            .setPositiveButton(R.string.dialog_large_canvas_warning_positive_button_text) { _, _ ->
                                if (checkBox.isChecked) {
                                    (requireActivity() as MainActivity).showLargeCanvasSizeWarning =
                                        false

                                    with((requireActivity() as MainActivity).sharedPreferenceObject.edit()) {
                                        putBoolean(
                                            StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_LARGE_CANVAS_SIZE_WARNING_IDENTIFIER,
                                            (requireActivity() as MainActivity).showLargeCanvasSizeWarning
                                        )
                                        apply()
                                    }
                                }

                                caller.onDoneButtonPressed(
                                    title,
                                    widthValue,
                                    heightValue
                                )
                            }
                            .setNegativeButton(R.string.generic_cancel, null)

                        alertDialog.show()
                    } else {
                        caller.onDoneButtonPressed(
                            title,
                            widthValue,
                            heightValue
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