package com.realtomjoney.pyxlmoose.models

import com.realtomjoney.pyxlmoose.enums.BrushInstruction

data class Brush(val brushName: String, val brushInstructionData: List<BrushInstructionCommand>, val brushImage: Int) {
    fun convertBrushInstructionDataToXYPositionData(from: Coordinates): List<Coordinates> {
        val instructions = mutableListOf<Coordinates>()

        for (brushInstruction in this.brushInstructionData) {
            when (brushInstruction.brushInstruction) {
                BrushInstruction.EXPAND_TOP -> {
                    instructions.add(Coordinates(from.x, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM -> {
                    instructions.add(Coordinates(from.x, from.y - brushInstruction.by))
                }
                BrushInstruction.EXPAND_LEFT -> {
                    instructions.add(Coordinates(from.x - brushInstruction.by, from.y))
                }
                BrushInstruction.EXPAND_RIGHT -> {
                    instructions.add(Coordinates(from.x + brushInstruction.by, from.y))
                }
                BrushInstruction.EXPAND_TOP_RIGHT -> {
                    instructions.add(Coordinates(from.x + brushInstruction.by, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_TOP_LEFT -> {
                    instructions.add(Coordinates(from.x - brushInstruction.by, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM_RIGHT -> {
                    instructions.add(Coordinates(from.x - brushInstruction.by, from.y - brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM_LEFT -> {
                    instructions.add(Coordinates(from.x + brushInstruction.by, from.y - brushInstruction.by))
                }
            }
        }

        return instructions
    }
}