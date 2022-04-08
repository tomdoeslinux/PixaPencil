package com.therealbluepandabear.pixapencil.activities.canvas

import android.view.Menu
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.disable

fun CanvasActivity.extendedOnCreateOptionsMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.activity_canvas_top_app_menu, _menu)

    if (_menu != null) {
        menu = _menu
        menu.findItem(R.id.activityCanvasTopAppMenu_pixel_perfect_item).isChecked = outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelPerfectMode
        menu.findItem(R.id.activityCanvasTopAppMenu_undo).disable()
        menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).disable()
    }

    return true
}