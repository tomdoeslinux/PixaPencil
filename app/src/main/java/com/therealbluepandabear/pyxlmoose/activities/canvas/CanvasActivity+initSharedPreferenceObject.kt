package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.content.Context

fun CanvasActivity.initSharedPreferenceObject() {
    sharedPreferenceObject = this.getPreferences(Context.MODE_PRIVATE)
}