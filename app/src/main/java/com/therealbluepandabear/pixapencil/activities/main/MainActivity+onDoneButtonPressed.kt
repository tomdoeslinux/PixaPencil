package com.therealbluepandabear.pixapencil.activities.main

import android.content.Intent
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MainActivity.extendedOnDoneButtonPressed(projectTitle: String, width: Int, height: Int) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.Extras.ProjectTitleExtra, projectTitle)
            .putExtra(StringConstants.Extras.WidthExtra, width)
            .putExtra(StringConstants.Extras.HeightExtra, height)
    )
    title = getString(R.string.app_name)
}