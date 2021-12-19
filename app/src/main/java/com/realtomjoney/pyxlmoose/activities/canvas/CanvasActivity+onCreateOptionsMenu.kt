package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.Menu
import com.realtomjoney.pyxlmoose.R

fun CanvasActivity.extendedOnCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.app_menu, menu)

    if (index != -1) setMenuItemIcon(menu!!.getItem(4), R.drawable.ic_baseline_save_24, "Save")

    return true
}