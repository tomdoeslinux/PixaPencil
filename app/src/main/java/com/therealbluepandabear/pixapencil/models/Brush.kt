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

package com.therealbluepandabear.pixapencil.models

import com.therealbluepandabear.pixapencil.enums.BrushInstruction

data class Brush(val brushInstructionData: List<BrushInstructionCommand>, val brushImage: Int) {
    var id: Int = 0

    fun convertBrushInstructionDataToXYPositionData(from: Coordinate): List<Coordinate> {
        val instructions = mutableListOf<Coordinate>()

        for (brushInstruction in brushInstructionData) {
            when (brushInstruction.brushInstruction) {
                BrushInstruction.ExpandTop -> {
                    instructions.add(Coordinate(from.x, from.y + brushInstruction.by))
                }

                BrushInstruction.ExpandBottom -> {
                    instructions.add(Coordinate(from.x, from.y - brushInstruction.by))
                }

                BrushInstruction.ExpandLeft -> {
                    instructions.add(Coordinate(from.x - brushInstruction.by, from.y))
                }

                BrushInstruction.ExpandRight -> {
                    instructions.add(Coordinate(from.x + brushInstruction.by, from.y))
                }

                BrushInstruction.ExpandTopRight -> {
                    instructions.add(Coordinate(from.x + brushInstruction.by, from.y + brushInstruction.by))
                }

                BrushInstruction.ExpandTopLeft -> {
                    instructions.add(Coordinate(from.x - brushInstruction.by, from.y + brushInstruction.by))
                }

                BrushInstruction.ExpandBottomRight -> {
                    instructions.add(Coordinate(from.x - brushInstruction.by, from.y - brushInstruction.by))
                }

                BrushInstruction.ExpandBottomLeft -> {
                    instructions.add(Coordinate(from.x + brushInstruction.by, from.y - brushInstruction.by))
                }
            }
        }

        return instructions
    }
}