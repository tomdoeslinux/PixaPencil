package com.therealbluepandabear.pixapencil.extensions

fun <T> ArrayDeque<T>.doAddLast(element: T) {
    if (size >= 100) {
        removeFirst()
    }

    add(element)
}