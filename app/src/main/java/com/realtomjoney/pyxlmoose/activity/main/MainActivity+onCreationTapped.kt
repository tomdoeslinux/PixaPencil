package com.realtomjoney.pyxlmoose.activity.main

import android.content.Intent
import com.realtomjoney.pyxlmoose.PixelArt
import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.activity.canvas.CanvasActivity

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    startActivity(
        Intent(this, CanvasActivity::class.java).putExtra("INDEX", PixelArtDatabase.toList()
            .indexOf(param)))
}