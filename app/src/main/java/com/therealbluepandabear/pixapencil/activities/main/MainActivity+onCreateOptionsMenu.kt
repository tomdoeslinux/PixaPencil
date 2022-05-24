package com.therealbluepandabear.pixapencil.activities.main

import android.graphics.Color
import android.view.Menu
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.changeColor

fun MainActivity.extendedOnCreateOptionsMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.activity_main_top_app_menu, _menu)
    
    if (_menu != null) {
        menu = _menu
        menu.findItem(R.id.activityMainTopAppMenu_save_project_item).icon.changeColor(Color.parseColor("#0099cc"))
        menu.findItem(R.id.activityMainTopAppMenu_dark_light_mode_item).icon.changeColor(Color.parseColor("#0099cc"))
    }

    return true
}