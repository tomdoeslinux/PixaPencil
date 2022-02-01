package com.realtomjoney.pyxlmoose.fragments.newcanvas

import androidx.constraintlayout.widget.ConstraintLayout
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun NewCanvasFragment.setOnClickListeners() {
    binding.fragmentNewCanvasDoneButton.setOnClickListener {
        val parsedSpanCountValue =  Integer.parseInt(binding.fragmentNewCanvasSpanCountTextInputEditText.text.toString())

        if (parsedSpanCountValue <= 0) {
            (root as ConstraintLayout).showSnackbar(StringConstants.EX_INVALID_SPAN_COUNT, SnackbarDuration.DEFAULT)
        } else {
            try {
                caller.onDoneButtonPressed(
                    Integer.parseInt(binding.fragmentNewCanvasSpanCountTextInputEditText.text.toString()),
                    binding.fragmentNewCanvasSpanCountTextInputEditText,
                    binding.fragmentNewCanvasProjectTitleTextInputEditText
                )
            } catch (ex: Exception) {
                (root as ConstraintLayout).showSnackbar(
                    StringConstants.EX_INVALID_SPAN_COUNT,
                    SnackbarDuration.DEFAULT
                )
            }
        }
    }
}