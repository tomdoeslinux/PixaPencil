package com.therealbluepandabear.pixapencil.activities.main

import android.content.Intent
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.Extras.INDEX_EXTRA, pixelArtData.indexOf(param))
            .putExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA, param.title))
}