package com.therealbluepandabear.pixapencil.activities.main

import android.content.Intent
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.extendedOnDoneButtonPressed(projectTitle: String, width: Int, height: Int, spotLightInProgress: Boolean) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA, projectTitle)
            .putExtra(StringConstants.Extras.WIDTH_EXTRA, width)
            .putExtra(StringConstants.Extras.HEIGHT_EXTRA, height)
            .putExtra(StringConstants.Extras.SPOTLIGHT_IN_PROGRESS_EXTRA, spotLightInProgress)
    )
}