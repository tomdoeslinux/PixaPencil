package com.therealbluepandabear.pyxlmoose.activities.main

import android.os.StrictMode

fun setVmPolicy() {
    val builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())
}