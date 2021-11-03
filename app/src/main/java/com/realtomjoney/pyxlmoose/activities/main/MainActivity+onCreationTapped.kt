package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    startActivity(
        Intent(this, CanvasActivity::class.java).putExtra("INDEX", PixelArtDatabase.toList()
            .indexOf(param)))
}