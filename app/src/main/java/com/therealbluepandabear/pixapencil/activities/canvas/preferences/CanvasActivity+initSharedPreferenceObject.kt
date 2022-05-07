package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import android.content.Context
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject

fun CanvasActivity.initSharedPreferenceObject() {
    sharedPreferenceObject = this.getPreferences(Context.MODE_PRIVATE)
}