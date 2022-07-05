package com.therealbluepandabear.pixapencil.activities.canvas.oncreate.addMenuProvider

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.addMenuProvider() {
    addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            this@addMenuProvider.onCreateMenu(menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return this@addMenuProvider.onMenuItemSelected(menuItem)
        }
    })
}