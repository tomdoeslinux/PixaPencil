package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.ObjectConstants

fun CanvasActivity.setObjectGlobalScopeLifecycleOwner() {
    ObjectConstants.ObjectGlobalScopeLifecycleOwner = this
}