package com.therealbluepandabear.pixapencil.activities.main.oncreate

import android.Manifest
import androidx.core.app.ActivityCompat
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun MainActivity.requestPermissions() {
    val requestCode = 1
    ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
}