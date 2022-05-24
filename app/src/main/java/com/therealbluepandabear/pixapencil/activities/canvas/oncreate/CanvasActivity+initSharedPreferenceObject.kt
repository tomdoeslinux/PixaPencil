package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.Context
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.initSharedPreferenceObject() {
    sharedPreferenceObject = getPreferences(Context.MODE_PRIVATE)
}