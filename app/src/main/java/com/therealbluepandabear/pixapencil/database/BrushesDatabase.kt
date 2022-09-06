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

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.BrushInstruction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.BrushInstructionCommand

object BrushesDatabase {
    private val database = mutableListOf<Brush>()

    private fun addBrush(brush: Brush) = database.add(brush)

    fun toList(): List<Brush> {
        return database.toList()
    }

    init {
        val brushesData = listOf(
            Brush(listOf(), R.drawable.brush_0),

            Brush(listOf(
                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTop, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottom, 1),
            ), R.drawable.brush_1),

            Brush(listOf(
                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTop, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottom, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottomLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottomRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTopLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTopRight, 1),
            ), R.drawable.brush_2),

            Brush(listOf(
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 2),

                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandLeft, 2),
            ), R.drawable.brush_3),

            Brush(listOf(
                BrushInstructionCommand(BrushInstruction.ExpandBottomLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottomRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTopLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTopRight, 1),

                BrushInstructionCommand(BrushInstruction.ExpandLeft, 2),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 2),
                BrushInstructionCommand(BrushInstruction.ExpandTop, 2),
                BrushInstructionCommand(BrushInstruction.ExpandBottom, 2),

                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTop, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottom, 1),
            ), R.drawable.brush_4))

        for (brush in brushesData) {
            brush.id = brushesData.indexOf(brush)
            addBrush(brush)
        }
    }
}