package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun CanvasActivity.getExtras() {
    index = intent.getIntExtra(StringConstants.IndexExtra, -1)
    title = (intent.getStringExtra(StringConstants.ProjectTitleExtra))
    width = intent.getIntExtra(StringConstants.WidthExtra, width)
    height = intent.getIntExtra(StringConstants.HeightExtra, width)
    projectTitle = title.toString()
}