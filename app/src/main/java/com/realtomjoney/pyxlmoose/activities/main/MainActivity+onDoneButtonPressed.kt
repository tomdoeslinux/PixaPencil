package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnDoneButtonPressed(spanCount: Int, titleEditText: TextInputEditText) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.SPAN_COUNT_EXTRA, Integer.parseInt(spanCount.toString()))
            .putExtra(StringConstants.PROJECT_TITLE_EXTRA, titleEditText.text.toString())
    )

    title = StringConstants.APP_NAME
    currentFragmentInstance = null
}