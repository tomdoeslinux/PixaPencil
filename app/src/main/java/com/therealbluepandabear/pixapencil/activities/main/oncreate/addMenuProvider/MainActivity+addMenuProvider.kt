package com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.core.view.children
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun MainActivity.addMenuProvider() {
    addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            this@addMenuProvider.onCreateMenu(menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return this@addMenuProvider.onMenuItemSelected(menuItem)
        }
    })
}