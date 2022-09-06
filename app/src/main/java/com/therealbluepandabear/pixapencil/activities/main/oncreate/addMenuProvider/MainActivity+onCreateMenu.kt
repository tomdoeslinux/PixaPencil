/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider

import android.graphics.Color
import android.view.Menu
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.changeColor

fun MainActivity.onCreateMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.activity_main_top_app_menu, _menu)
    
    if (_menu != null) {
        menu = _menu
        menu.findItem(R.id.activityMainTopAppMenu_save_project_item).icon?.changeColor(Color.parseColor("#0099cc"))
        menu.findItem(R.id.activityMainTopAppMenu_dark_light_mode_item).icon?.changeColor(Color.parseColor("#0099cc"))
    }

    return true
}