package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.getUriExtra
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.getExtras() {
    index = intent.getIntExtra(StringConstants.Extras.INDEX_EXTRA, -1)
    title = intent.getStringExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA)
    width = intent.getIntExtra(StringConstants.Extras.WIDTH_EXTRA, width)
    height = intent.getIntExtra(StringConstants.Extras.HEIGHT_EXTRA, height)
    spotLightInProgress = intent.getBooleanExtra(StringConstants.Extras.SPOTLIGHT_IN_PROGRESS_EXTRA, false)
    projectTitle = title?.toString() ?: projectTitle

    val uriStr = intent.getUriExtra(StringConstants.Extras.BITMAP_URI_EXTRA)

    if (uriStr != null) {
        uri = uriStr
    }

    if (index == -1) {
        viewModel.saved = false
    } else {
        width = AppData.pixelArtDB.dao().getAllNoLiveData()[index].width
        height = AppData.pixelArtDB.dao().getAllNoLiveData()[index].height
    }
}