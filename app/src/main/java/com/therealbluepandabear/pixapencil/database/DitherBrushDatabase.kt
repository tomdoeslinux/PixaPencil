package com.therealbluepandabear.pixapencil.database

import android.util.Log
import com.therealbluepandabear.pixapencil.models.DitherBrush

object DitherBrushDatabase {
    private val database = mutableListOf<DitherBrush>()

    private fun addDitherBrush(ditherBrush: DitherBrush) = database.add(ditherBrush)

    fun toList(): List<DitherBrush> {
        return database.toList()
    }

    init {
        val ditherBrushData = listOf(
            DitherBrush {
                (it.x + it.y) % 2 == 0
            },

            DitherBrush {
                it.x % 3 == 0
            },

            DitherBrush {
                it.y % 3 == 0
            },

            DitherBrush {
                (it.x + it.y) % 3 != 0
            },

            DitherBrush {
                (it.x + it.y) % 3 == 0
            },

            DitherBrush {
                it.x % 3 == 0 || it.y % 3 == 0
            },

            DitherBrush {
                it.x % 3 != 0 || it.y % 3 != 0
            },

            DitherBrush {
                it.x % 3 != 0 && it.y % 3 != 0
            },

            DitherBrush {
                it.x % 3 == 0 || it.y % 3 != 0
            },

            DitherBrush {
                (it.x + it.y) % 4 != 0
            },

            DitherBrush {
                (it.x + it.y) % 4 == 0 || (it.x - it.y) % 4 == 0
            },

            DitherBrush {
                !((it.x + it.y) % 4 == 0 || (it.x - it.y) % 4 == 0)
            },

            DitherBrush {
                it.y % 3 == 0 || it.x % 2 == 0
            },

            DitherBrush {
                it.x % 3 == 0 || it.y % 2 == 0
            },

            DitherBrush {
                ((it.x + it.y) % 2 == 0) || (it.y % 3 == 0)
            },

            DitherBrush {
                ((it.x + it.y) % 2 == 0) || (it.x % 3 == 0)
            },

            DitherBrush {
                ((it.x + it.y) % 2 == 0) || (it.x % 3 == 0 || it.y % 3 == 0 )
            },
        )

        for (ditherBrush in ditherBrushData) {
            ditherBrush.id = ditherBrushData.indexOf(ditherBrush)
            Log.d("BEPPER", ditherBrush.id.toString())
            addDitherBrush(ditherBrush)
        }
    }
}