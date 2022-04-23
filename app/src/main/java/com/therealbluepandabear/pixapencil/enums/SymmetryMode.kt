package com.therealbluepandabear.pixapencil.enums

enum class SymmetryMode(val symmetryName: String) {
    None("None"),
    Horizontal("Horizontal"),
    Vertical("Vertical"),
    Quad("Quad");

    companion object {
        val defaultSymmetryMode: SymmetryMode = None
    }
}