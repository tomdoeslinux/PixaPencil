package com.therealbluepandabear.pixapencil.activities.main

import android.content.Context

fun MainActivity.initSharedPreferencesObject() {
    sharedPreferenceObject = this.getPreferences(Context.MODE_PRIVATE)
}