package com.therealbluepandabear.pixapencil.models

import com.therealbluepandabear.pixapencil.enums.BrushInstruction

data class BrushInstructionCommand(val brushInstruction: BrushInstruction, val by: Int)
