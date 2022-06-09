package com.therealbluepandabear.pixapencil.enums

enum class SymmetryMode {
    None,
    Horizontal,
    Vertical,
    Quad,
    Octal;

    companion object {
        val defaultSymmetryMode: SymmetryMode = None
    }
}