package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.getExtras() {
    index = intent.getIntExtra(StringConstants.Extras.IndexExtra, -1)
    title = intent.getStringExtra(StringConstants.Extras.ProjectTitleExtra)
    width = intent.getIntExtra(StringConstants.Extras.WidthExtra, width)
    height = intent.getIntExtra(StringConstants.Extras.HeightExtra, width)
    spotLightInProgress = intent.getBooleanExtra(StringConstants.Extras.SpotLightInProgressExtra, false)

    if (title != null) {
        projectTitle = title.toString()
    } else {
        title = ""
    }

    if (index == -1) {
        saved = false
    }
}