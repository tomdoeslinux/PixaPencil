package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.Menu
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedOnCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.app_menu, menu)
    return true
}