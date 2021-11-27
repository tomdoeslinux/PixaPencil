package com.realtomjoney.pyxlmoose.extensions

import android.view.View
import android.view.ViewGroup

fun ViewGroup.doSomethingWithChildElements(func: (View) -> Unit) {
    val childCount = this.childCount
    var view: View?
    for (i in 0 until childCount) {
        view = this.getChildAt(i)
        func(view)
    }
}