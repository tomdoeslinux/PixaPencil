package com.therealbluepandabear.pixapencil.utility

import java.util.*

object InternalBitmapFileNameGenerator {
    fun generate(name: String): String {
        val uuid = UUID.randomUUID()
        val bitmapExtensionIdentifier = "bmp"

        return "$name+$uuid.$bitmapExtensionIdentifier"
    }
}