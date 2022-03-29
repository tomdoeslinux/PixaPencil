package com.therealbluepandabear.pixapencil.activities.canvas

import android.view.Menu
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.disable

fun CanvasActivity.extendedOnCreateOptionsMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.app_menu, _menu)

    if (_menu != null) {
        menu = _menu
        menu.findItem(R.id.appMenu_pixel_perfect_item).isChecked = outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode
        menu.findItem(R.id.appMenu_undo).disable()
        menu.findItem(R.id.appMenu_redo_item).disable()
    }

    if (index != -1) {
        setMenuItemIcon(_menu!!.getItem(4), R.drawable.ic_baseline_save_24, "Save")
    }

    return true
}