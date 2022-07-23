package com.therealbluepandabear.pixapencil.extensions

import android.content.Intent
import android.net.Uri

fun Intent.putUriExtra(name: String, value: Uri): Intent {
    return putExtra(name, value.toString())
}

fun Intent.getUriExtra(name: String): Uri? {
    return if (getStringExtra(name) != null) {
        Uri.parse(getStringExtra(name))
    } else {
        null
    }
}