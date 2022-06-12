package com.therealbluepandabear.pixapencil.activities.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun MainActivity.requestPermissions() {
    val requestCode = 1
    ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
}