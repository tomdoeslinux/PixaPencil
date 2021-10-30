package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.Menu
import com.realtomjoney.pyxlmoose.R

fun extendedOnCreateOptionsMenu(menu: Menu?, context: CanvasActivity): Boolean {
    val inflater = context.menuInflater
    inflater.inflate(R.menu.app_menu, menu)
    return true
}