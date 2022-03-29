package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun extendedOnRedoActionCompleted(undoStack: List<BitmapAction>) {
    if (undoStack.isEmpty()) {
        menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).disable()
    }
}