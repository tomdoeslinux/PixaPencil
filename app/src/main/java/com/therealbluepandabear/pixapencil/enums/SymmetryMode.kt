package com.therealbluepandabear.pixapencil.enums

enum class SymmetryMode(val symmetryName: String) {
    None("None"),
    Horizontal("Horizontal"),
    Vertical("Vertical"),
    Quad("Quad"),
    Octal("Octal");

    companion object {
        val defaultSymmetryMode: SymmetryMode = None
    }
}