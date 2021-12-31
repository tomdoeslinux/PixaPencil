package com.realtomjoney.pyxlmoose.database

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.enums.BrushInstruction
import com.realtomjoney.pyxlmoose.models.Brush

object BrushesDatabase {
    private val database = mutableListOf<Brush>()

    private fun addBrush(brush: Brush) = database.add(brush)

    fun toList() = database.toList()

    init {
        val brushesData = listOf(
            Brush("Default Brush", listOf(), R.drawable.default_brush),
            Brush("Brush 1", listOf(BrushInstruction.EXPAND_LEFT, BrushInstruction.EXPAND_RIGHT, BrushInstruction.EXPAND_TOP, BrushInstruction.EXPAND_BOTTOM), R.drawable.brush_1),
            Brush("Brush 2", listOf(BrushInstruction.EXPAND_LEFT, BrushInstruction.EXPAND_RIGHT, BrushInstruction.EXPAND_TOP, BrushInstruction.EXPAND_BOTTOM, BrushInstruction.EXPAND_BOTTOM_LEFT, BrushInstruction.EXPAND_BOTTOM_RIGHT, BrushInstruction.EXPAND_TOP_LEFT,  BrushInstruction.EXPAND_TOP_RIGHT), R.drawable.brush_2),
            Brush("Brush 3", listOf(BrushInstruction.EXPAND_BOTTOM_LEFT, BrushInstruction.EXPAND_BOTTOM_RIGHT, BrushInstruction.EXPAND_TOP_LEFT,  BrushInstruction.EXPAND_TOP_RIGHT), R.drawable.brush_3),
            )

        for (brush in brushesData) addBrush(brush)
    }
}