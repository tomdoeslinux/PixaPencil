package com.therealbluepandabear.pixapencil.activities.main

import android.content.Intent
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    startActivity(
        Intent(this, CanvasActivity::class.java)
            .putExtra(StringConstants.Extras.ProjectTitleExtra, param.title)
            .putExtra(StringConstants.Extras.PixelArtIdExtra, param.objId)
    )
}