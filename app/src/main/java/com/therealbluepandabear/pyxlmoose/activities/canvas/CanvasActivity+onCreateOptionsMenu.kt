package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.view.Menu
import com.realtomjoney.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.extensions.disable

fun CanvasActivity.extendedOnCreateOptionsMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.app_menu, _menu)

    if (_menu != null) {
        menu = _menu
        menu.findItem(R.id.pixel_perfect).isChecked = outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode
        menu.findItem(R.id.undo).disable()
        menu.findItem(R.id.redo).disable()
    }

    if (index != -1) {
        setMenuItemIcon(_menu!!.getItem(4), R.drawable.ic_baseline_save_24, "Save")
    }

    return true
}