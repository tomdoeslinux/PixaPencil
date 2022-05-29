package com.therealbluepandabear.pixapencil.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import com.therealbluepandabear.pixapencil.enums.OutputCode

/** Thank you to to rjrjr on StackOverflow - the code here is  based off of their solution. It may be modified a bit.
 *
 * - [Link to rjrjr's profile](https://stackoverflow.com/users/350970/rjrjr)
 * - [Original StackOverFlow post](https://stackoverflow.com/questions/9891360/getting-activity-from-context-in-android)
 * **/

tailrec fun Context.activity(): Activity? = when (this) {
    is Activity -> this
    else -> (this as? ContextWrapper)?.baseContext?.activity()
}

fun Context.copyToClipboard(label: String, text: String): OutputCode {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText(label, text))

    return if (clipboard.primaryClip?.getItemAt(0)?.text == text) {
        OutputCode.Success
    } else {
        OutputCode.Failure
    }
}