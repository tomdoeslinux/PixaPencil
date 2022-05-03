package com.therealbluepandabear.pixapencil.activities.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/** Thank you to to Muhammad on StackOverflow - the code here is based off of their solution. It may be modified a bit.
 *
 * - [Link to Muhammad's profile](https://stackoverflow.com/users/1966247/muhammad)
 * - [Original StackOverFlow post](https://stackoverflow.com/questions/33666071/android-marshmallow-request-permission?answertab=modifieddesc#tab-top)
 * **/

private fun MainActivity.askForPermissionOnSDK23(permission: String, requestCode: Int) {
    if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
    }
}
fun MainActivity.requestPermissions() {
    val requestCode = 1

    if (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.M) {
        ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
    } else {
        askForPermissionOnSDK23(Manifest.permission.WRITE_EXTERNAL_STORAGE, requestCode)
    }
}