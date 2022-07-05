package com.therealbluepandabear.pixapencil.activities.main.oncreate

import android.os.StrictMode

fun setVmPolicy() {
    val builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())
}