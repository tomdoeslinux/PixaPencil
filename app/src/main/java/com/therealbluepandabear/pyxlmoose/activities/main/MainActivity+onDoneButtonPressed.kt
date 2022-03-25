package com.therealbluepandabear.pyxlmoose.activities.main

import android.content.Intent
import com.therealbluepandabear.pyxlmoose.activities.canvas.CanvasActivity
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnDoneButtonPressed(projectTitle: String, width: Int, height: Int) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.ProjectTitleExtra, projectTitle)
            .putExtra(StringConstants.WidthExtra, width)
            .putExtra(StringConstants.HeightExtra, height)
    )

    title = StringConstants.AppName
    currentFragmentInstance = null
}