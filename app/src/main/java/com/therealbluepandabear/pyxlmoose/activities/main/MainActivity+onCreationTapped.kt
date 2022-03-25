package com.therealbluepandabear.pyxlmoose.activities.main

import android.content.Intent
import com.therealbluepandabear.pyxlmoose.activities.canvas.CanvasActivity
import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.models.PixelArt
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this) {
        startActivity(
            Intent(context, CanvasActivity::class.java)
                .putExtra(StringConstants.IndexExtra, it.indexOf(param))
                .putExtra(StringConstants.ProjectTitleExtra, param.title)
        )
    }
}