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
            Brush("Default Brush", listOf(), R.drawable.default_brush),
            Brush("Brush 1", listOf(
                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTop, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottom, 1),
            ), R.drawable.brush_1),
            Brush("Brush 2", listOf(
                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTop, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottom, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottomLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandBottomRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTopLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandTopRight, 1),
            ), R.drawable.brush_2),
            Brush("Brush 4", listOf(
                BrushInstructionCommand(BrushInstruction.ExpandRight, 1),
                BrushInstructionCommand(BrushInstruction.ExpandRight, 2),

                BrushInstructionCommand(BrushInstruction.ExpandLeft, 1),
                BrushInstructionCommand(BrushInstruction.ExpandLeft, 2),
            ), R.drawable.brush_4),
            Brush("Brush 6", listOf(
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
            ), R.drawable.brush_6))

        for (brush in brushesData) addBrush(brush)
    }
}