package com.realtomjoney.pyxlmoose.models

import android.util.Log
import com.realtomjoney.pyxlmoose.enums.BrushInstruction

data class Brush(val brushName: String, val brushInstructionData: List<BrushInstructionCommand>, val brushImage: Int) {
    fun convertBrushInstructionDataToXYPositionData(spanCount: Int, from: XYPosition): List<XYPosition> {
        val instructions = mutableListOf<XYPosition>()

        for (brushInstruction in this.brushInstructionData) {
            when (brushInstruction.brushInstruction) {
                BrushInstruction.EXPAND_TOP -> {
                    if (from.y < spanCount - brushInstruction.by + 1) instructions.add(XYPosition(from.x, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM -> {
                    if (from.y > brushInstruction.by) instructions.add(XYPosition(from.x, from.y - brushInstruction.by))
                }
                BrushInstruction.EXPAND_LEFT -> {
                    if (from.x > brushInstruction.by) instructions.add(XYPosition(from.x - brushInstruction.by, from.y))
                }
                BrushInstruction.EXPAND_RIGHT -> {
                    if (from.x + brushInstruction.by - 1 < spanCount) instructions.add(XYPosition(from.x + brushInstruction.by, from.y))
                }
                BrushInstruction.EXPAND_TOP_RIGHT -> {
                    if (from.x + brushInstruction.by - 1 < spanCount && from.y < spanCount - brushInstruction.by + 1) instructions.add(XYPosition(from.x + brushInstruction.by, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_TOP_LEFT -> {
                    if (from.x > brushInstruction.by && from.y < spanCount - brushInstruction.by + 1) instructions.add(XYPosition(from.x - brushInstruction.by, from.y + brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM_RIGHT -> {
                    if (from.x > brushInstruction.by && from.y > brushInstruction.by) instructions.add(XYPosition(from.x - brushInstruction.by, from.y - brushInstruction.by))
                }
                BrushInstruction.EXPAND_BOTTOM_LEFT -> {
                    if (from.x + brushInstruction.by - 1 < spanCount && from.y > brushInstruction.by) instructions.add(XYPosition(from.x + brushInstruction.by, from.y - brushInstruction.by))
                }
            }
        }

        return instructions
    }
}