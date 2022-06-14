package com.therealbluepandabear.pixapencil.activities.main

import android.Manifest
import androidx.core.app.ActivityCompat

fun MainActivity.requestPermissions() {
    val requestCode = 1
    ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
}