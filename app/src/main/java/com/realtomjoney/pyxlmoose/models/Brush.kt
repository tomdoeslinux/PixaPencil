package com.realtomjoney.pyxlmoose.models

import com.realtomjoney.pyxlmoose.enums.BrushInstruction

data class Brush(val brushName: String, val brushInstructionData: List<BrushInstruction>, val brushImage: Int) {
    fun convertBrushInstructionDataToXYPositionData(spanCount: Int, from: XYPosition): List<XYPosition> {
        val instructions = mutableListOf<XYPosition>()

        for (brushInstruction in this.brushInstructionData) {
            when (brushInstruction) {
                BrushInstruction.EXPAND_TOP -> {
                    if (from.y < spanCount) instructions.add(XYPosition(from.x, from.y + 1))
                }
                BrushInstruction.EXPAND_BOTTOM -> {
                    if (from.y > 1) instructions.add(XYPosition(from.x, from.y - 1))
                }
                BrushInstruction.EXPAND_LEFT -> {
                    if (from.x > 1) instructions.add(XYPosition(from.x - 1, from.y))
                }
                BrushInstruction.EXPAND_RIGHT -> {
                    if (from.x < spanCount) instructions.add(XYPosition(from.x + 1, from.y))
                }
                BrushInstruction.EXPAND_TOP_RIGHT -> {
                    if (from.x < spanCount && from.y < spanCount) instructions.add(XYPosition(from.x + 1, from.y + 1))
                }
                BrushInstruction.EXPAND_TOP_LEFT -> {
                    if (from.x > 1 && from.y < spanCount) instructions.add(XYPosition(from.x - 1, from.y + 1))
                }
                BrushInstruction.EXPAND_BOTTOM_RIGHT -> {
                    if (from.x > 1 && from.y > 1) instructions.add(XYPosition(from.x - 1, from.y - 1))
                }
                BrushInstruction.EXPAND_BOTTOM_LEFT -> {
                    if (from.x < spanCount && from.y > 1) instructions.add(XYPosition(from.x + 1, from.y - 1))
                }
            }
        }

        return instructions
    }
}