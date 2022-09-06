/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
            addDitherBrush(ditherBrush)
        }
    }
}