package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnDoneButtonPressed(projectTitle: String, width: Int, height: Int) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.PROJECT_TITLE_EXTRA, projectTitle)
            .putExtra(StringConstants.WIDTH_EXTRA, width)
            .putExtra(StringConstants.HEIGHT_EXTRA, height)
    )

    title = StringConstants.APP_NAME
    currentFragmentInstance = null
}