package com.realtomjoney.pyxlmoose.fragments.tools

import com.realtomjoney.pyxlmoose.utility.StringConstants

fun setOnClickListeners() {
    binding.apply {
        fragmentToolsPencilButton.setOnClickListener {
            caller.onToolTapped(StringConstants.PENCIL_TOOL_IDENTIFIER)
        }

        fragmentToolsFillButton.setOnClickListener {
            caller.onToolTapped(StringConstants.FILL_TOOL_IDENTIFIER)
        }

        fragmentToolsVerticalMirrorButton.setOnClickListener {
            caller.onToolTapped(StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER)
        }

        fragmentToolsHorizontalMirrorButton.setOnClickListener {
            caller.onToolTapped(StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER)
        }

        fragmentToolsLineButton.setOnClickListener {
            caller.onToolTapped(StringConstants.LINE_TOOL_IDENTIFIER)
        }

        fragmentToolsRectangleButton.setOnClickListener {
            caller.onToolTapped(StringConstants.RECTANGLE_TOOL_IDENTIFIER)
        }

        fragmentToolsOutlinedRectangleButton.setOnClickListener {
            caller.onToolTapped(StringConstants.OUTLINED_RECTANGLE_TOOL_IDENTIFIER)
        }

        fragmentToolsDarkenButton.setOnClickListener {
            caller.onToolTapped(StringConstants.DARKEN_TOOL_IDENTIFIER)
        }

        fragmentToolsLightenButton.setOnClickListener {
            caller.onToolTapped(StringConstants.LIGHTEN_TOOL_IDENTIFIER)
        }

        fragmentToolsResetCanvasButton.setOnClickListener {
            caller.onToolTapped(StringConstants.CLEAR_CANVAS_TOOL_IDENTIFIER)
        }

        fragmentToolsChangeBackgroundButton.setOnClickListener {
            caller.onToolTapped(StringConstants.CHANGE_BACKGROUND_TOOL_IDENTIFIER)
        }

        fragmentToolsColorPickerButton.setOnClickListener {
            caller.onToolTapped(StringConstants.COLOR_PICKER_TOOL_IDENTIFIER)
        }

        fragmentToolsFindAndReplaceButton.setOnClickListener {
            caller.onToolTapped(StringConstants.FIND_AND_REPLACE_TOOL_IDENTIFIER)
        }

        fragmentToolsEraseButton.setOnClickListener {
            caller.onToolTapped(StringConstants.ERASE_TOOL_IDENTIFIER)
        }

        fragmentToolsGridButton.setOnClickListener {
            caller.onToolTapped(StringConstants.GRID_TOOL_IDENTIFIER)
        }
    }
}