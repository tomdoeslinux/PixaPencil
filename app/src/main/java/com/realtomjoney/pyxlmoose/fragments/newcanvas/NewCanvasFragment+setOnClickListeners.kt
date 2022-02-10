package com.realtomjoney.pyxlmoose.fragments.newcanvas

import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.utility.StringConstants

const val SPAN_COUNT_MIN = 1
const val SPAN_COUNT_MAX = 10000

fun setOnClickListeners() {
    binding.apply {
        fragmentNewCanvasDoneButton.setOnClickListener {
            val parsedSpanCountValue =
                Integer.parseInt(fragmentNewCanvasSpanCountTextInputEditText.text.toString())

            if (parsedSpanCountValue !in SPAN_COUNT_MIN..SPAN_COUNT_MAX) {
                root.showSnackbar(
                    StringConstants.EX_INVALID_SPAN_COUNT,
                    SnackbarDuration.DEFAULT
                )
            } else {
                try {
                    caller.onDoneButtonPressed(
                        Integer.parseInt(fragmentNewCanvasSpanCountTextInputEditText.text.toString()),
                        fragmentNewCanvasSpanCountTextInputEditText,
                        fragmentNewCanvasProjectTitleTextInputEditText
                    )
                } catch (ex: Exception) {
                    root.showSnackbar(
                        StringConstants.EX_INVALID_SPAN_COUNT,
                        SnackbarDuration.DEFAULT
                    )
                }
            }
        }
    }
}