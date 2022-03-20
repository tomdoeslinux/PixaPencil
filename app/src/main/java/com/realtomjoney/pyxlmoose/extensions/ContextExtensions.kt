package com.realtomjoney.pyxlmoose.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

// from rjrjr @ https://stackoverflow.com/questions/9891360/getting-activity-from-context-in-android
tailrec fun Context.activity(): Activity? = when (this) {
    is Activity -> this
    else -> (this as? ContextWrapper)?.baseContext?.activity()
}