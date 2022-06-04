package com.therealbluepandabear.pixapencil.database

import com.therealbluepandabear.pixapencil.models.DitherBrush

object DitherBrushDatabase {
    private val database = mutableListOf<DitherBrush>()

    private fun addDitherBrush(ditherBrush: DitherBrush) = database.add(ditherBrush)

    fun toList(): List<DitherBrush> {
        return database.toList()
    }

    init {
        val ditherBrushData = listOf(
            DitherBrush(1) {
                (it.x + it.y) % 2 == 0
            },

            DitherBrush(2) {
                it.x % 3 == 0
            },

            DitherBrush(3) {
                it.y % 3 == 0
            },

            DitherBrush(4) {
                (it.x + it.y) % 3 != 0
            },

            DitherBrush(5) {
                (it.x + it.y) % 3 == 0
            },

            DitherBrush(6) {
                it.x % 3 == 0 || it.y % 3 == 0
            },

            DitherBrush(7) {
                it.x % 3 != 0 || it.y % 3 != 0
            },

            DitherBrush(8) {
                it.x % 3 != 0 && it.y % 3 != 0
            },

            DitherBrush(9) {
                it.x % 3 == 0 || it.y % 3 != 0
            },

            DitherBrush(10) {
                (it.x + it.y) % 4 != 0
            },

            DitherBrush(11) {
                (it.x + it.y) % 4 == 0 || (it.x - it.y) % 4 == 0
            },

            DitherBrush(12) {
                !((it.x + it.y) % 4 == 0 || (it.x - it.y) % 4 == 0)
            },

            DitherBrush(13) {
                it.y % 3 == 0 || it.x % 2 == 0
            },

            DitherBrush(14) {
                it.x % 3 == 0 || it.y % 2 == 0
            },
        )

        for (brush in ditherBrushData) {
            addDitherBrush(brush)
        }
    }
}