package com.realtomjoney.pyxlmoose.models

import com.realtomjoney.pyxlmoose.enums.BrushInstruction

data class Brush(val brushName: String, val brushInstructions: List<BrushInstruction>, val brushImage: Int)