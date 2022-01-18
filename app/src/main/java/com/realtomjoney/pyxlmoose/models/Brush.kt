package com.realtomjoney.pyxlmoose.models

import com.realtomjoney.pyxlmoose.enums.BrushInstruction

data class Brush(val brushName: String, val brushInstructionData: List<BrushInstructionCommand>, val brushImage: Int) {
    fun convertBrushInstructionDataToXYPositionData(from: XYPosition): List<XYPosition> {
        val instructions = mutableListOf<XYPosition>()

        for (brushInstruction in this.brushInstructionData) {
            when (brushInstruction.brushInstruction) {
                BrushInstruction.EXPAND_TOP -> {
                    instructions.add(XYPosition(from.x, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM -> {
                    instructions.add(XYPosition(from.x, from.y - brushInstruction.by))
                }
                BrushInstruction.EXPAND_LEFT -> {
                    instructions.add(XYPosition(from.x - brushInstruction.by, from.y))
                }
                BrushInstruction.EXPAND_RIGHT -> {
                    instructions.add(XYPosition(from.x + brushInstruction.by, from.y))
                }
                BrushInstruction.EXPAND_TOP_RIGHT -> {
                    instructions.add(XYPosition(from.x + brushInstruction.by, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_TOP_LEFT -> {
                    instructions.add(XYPosition(from.x - brushInstruction.by, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM_RIGHT -> {
                    instructions.add(XYPosition(from.x - brushInstruction.by, from.y - brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM_LEFT -> {
                    instructions.add(XYPosition(from.x + brushInstruction.by, from.y - brushInstruction.by))
                }
            }
        }

        return instructions
    }
}