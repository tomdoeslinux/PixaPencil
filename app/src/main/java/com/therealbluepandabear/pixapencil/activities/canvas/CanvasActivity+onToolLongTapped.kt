package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnToolLongTapped(toolName: String) {
    when (toolName) {
        StringConstants.PencilToolIdentifier -> {
            if (currentTool == Tools.PencilTool) {
                showSimpleInfoDialog(
                    StringConstants.PencilToolInfoTitle,
                    StringConstants.PencilToolInfoText)
            }
        }

        StringConstants.FillToolIdentifier  -> {
            if (currentTool == Tools.FillTool) {
                showSimpleInfoDialog(
                    StringConstants.FillToolInfoTitle,
                    StringConstants.FillToolInfoText)
            }
        }

        StringConstants.VerticalMirrorToolIdentifier -> {
            if (currentTool == Tools.VerticalMirrorTool) {
                showSimpleInfoDialog(
                    StringConstants.VerticalMirrorToolInfoTitle,
                    StringConstants.VerticalMirrorToolInfoText)
            }
        }

        StringConstants.HorizontalMirrorToolIdentifier -> {
            if (currentTool == Tools.HorizontalMirrorTool) {
                showSimpleInfoDialog(
                    StringConstants.HorizontalMirrorToolInfoTitle,
                    StringConstants.HorizontalMirrorToolInfoText)
            }
        }

        StringConstants.LineToolIdentifier -> {
            if (currentTool == Tools.LineTool) {
                showSimpleInfoDialog(
                    StringConstants.LineToolInfoTitle,
                    StringConstants.LineToolInfoText)
            }
        }

        StringConstants.RectangleToolIdentifier -> {
            if (currentTool == Tools.RectangleTool) {
                showSimpleInfoDialog(
                    StringConstants.RectangleToolInfoTitle,
                    StringConstants.RectangleToolInfoText)
            }
        }

        StringConstants.OutlinedRectangleToolIdentifier -> {
            if (currentTool == Tools.OutlinedRectangleTool) {
                showSimpleInfoDialog(
                    StringConstants.OutlinedRectangleToolInfoTitle,
                    StringConstants.OutlinedRectangleToolInfoText)
            }
        }

        StringConstants.SquareToolIdentifier -> {
            if (currentTool == Tools.SquareTool) {
                showSimpleInfoDialog(
                    StringConstants.SquareToolInfoTitle,
                    StringConstants.SquareToolInfoText)
            }
        }

        StringConstants.OutlinedSquareToolIdentifier -> {
            if (currentTool == Tools.OutlinedSquareTool) {
                showSimpleInfoDialog(
                    StringConstants.OutlinedSquareToolInfoTitle,
                    StringConstants.OutlinedSquareToolInfoText)
            }
        }

        StringConstants.CircleToolIdentifier -> {
            if (currentTool == Tools.CircleTool) {
                showSimpleInfoDialog(
                    StringConstants.CircleToolInfoTitle,
                    StringConstants.CircleToolInfoText)
            }
        }

        StringConstants.OutlinedCircleToolIdentifier -> {
            if (currentTool == Tools.OutlinedCircleTool) {
                showSimpleInfoDialog(
                    StringConstants.OutlinedCircleToolInfoTitle,
                    StringConstants.OutlinedCircleToolInfoText)
            }
        }

        StringConstants.SprayToolIdentifier -> {
            if (currentTool == Tools.SprayTool) {
                showSimpleInfoDialog(
                    StringConstants.SprayToolInfoTitle,
                    StringConstants.SprayToolInfoText)
            }
        }

        StringConstants.PolygonToolIdentifier -> {
            if (currentTool == Tools.PolygonTool) {
                showSimpleInfoDialog(
                    StringConstants.PolygonToolInfoTitle,
                    StringConstants.PolygonToolInfoText)
            }
        }

        StringConstants.DitherToolIdentifier -> {
            if (currentTool == Tools.DitherTool) {
                showSimpleInfoDialog(
                    StringConstants.DitherToolInfoTitle,
                    StringConstants.DitherToolInfoText)
            }
        }

        StringConstants.DarkenToolIdentifier  -> {
            if (currentTool == Tools.DarkenTool) {
                showSimpleInfoDialog(
                    StringConstants.DarkenToolInfoTitle,
                    StringConstants.DarkenToolInfoText)
            }
        }

        StringConstants.LightenToolIdentifier  -> {
            if (currentTool == Tools.LightenTool) {
                showSimpleInfoDialog(
                    StringConstants.LightenToolInfoTitle,
                    StringConstants.LightenToolInfoText)
            }
        }

        StringConstants.ColorPickerToolIdentifier -> {
            if (currentTool == Tools.ColorPickerTool) {
                showSimpleInfoDialog(
                    StringConstants.ColorPickerToolInfoTitle,
                    StringConstants.ColorPickerToolInfoText)
            }
        }

        StringConstants.EraseToolIdentifier -> {
            if (currentTool == Tools.EraseTool) {
                showSimpleInfoDialog(
                    StringConstants.EraseToolInfoTitle,
                    StringConstants.EraseToolInfoText)
            }
        }
    }
}