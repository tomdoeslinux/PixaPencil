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

package com.therealbluepandabear.pixapencil.activities.main

import android.view.View
import androidx.activity.OnBackPressedCallback

fun MainActivity.registerOnBackPressedDispatcherCallback() {
    onBackPressedDispatcher.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    // If we remove this, when the user would press the back button the bottom navigation view will disappear
                    binding.activityMainBottomNavigationView.visibility = View.VISIBLE

                    supportFragmentManager.popBackStackImmediate()
                } else {
                    this@registerOnBackPressedDispatcherCallback.finishAffinity()
                }
            }
        })
}