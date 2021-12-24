package com.realtomjoney.pyxlmoose.fragments.newcanvas

import androidx.constraintlayout.widget.ConstraintLayout
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar

fun NewCanvasFragment.setOnClickListeners() {
    binding.fragmentNewCanvasDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed(
                Integer.parseInt(binding.fragmentNewCanvasSpanCountTextInputEditText.text.toString()),
                binding.fragmentNewCanvasSpanCountTextInputEditText,
                binding.fragmentNewCanvasProjectTitleTextInputEditText
            )
        } catch (ex: Exception) {
            (root as ConstraintLayout).showSnackbar(ex.message.toString(), SnackbarDuration.DEFAULT)
        }
    }
}