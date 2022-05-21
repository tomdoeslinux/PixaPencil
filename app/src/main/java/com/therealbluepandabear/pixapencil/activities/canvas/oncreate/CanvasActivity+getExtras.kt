package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.getExtras() {
    title = intent.getStringExtra(StringConstants.Extras.ProjectTitleExtra)
    width = intent.getIntExtra(StringConstants.Extras.WidthExtra, width)
    height = intent.getIntExtra(StringConstants.Extras.HeightExtra, width)
    pixelArtId = intent.getIntExtra(StringConstants.Extras.PixelArtIdExtra, -1)

    if (title != null) {
        projectTitle = title.toString()
    } else {
        title = ""
    }
}