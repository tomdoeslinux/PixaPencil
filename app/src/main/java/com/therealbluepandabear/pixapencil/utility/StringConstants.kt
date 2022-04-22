package com.therealbluepandabear.pixapencil.utility

object StringConstants {
    const val AppName = "PixaPencil"

    object Extras {
        const val ProjectTitleExtra = "ProjectTitleExtra"
        const val WidthExtra = "WidthExtra"
        const val HeightExtra = "HeightExtra"
        const val IndexExtra = "IndexExtra"
    }

    object Identifiers {
        const val ColorFilterIdentifier = "ColorFilter"
        const val DarkenFilterIdentifier = "DarkenFilter"
        const val LightenFilterIdentifier = "LightenFilter"
        const val InvertFilterIdentifier = "InvertFilter"
        const val GrayscaleFilterIdentifier = "GrayscaleFilter"

        const val PencilToolIdentifier = "PencilTool"
        const val EraseToolIdentifier = "EraseTool"
        const val ColorPickerToolIdentifier = "ColorPickerTool"
        const val FillToolIdentifier = "FillTool"
        const val LineToolIdentifier = "LineTool"
        const val RectangleToolIdentifier = "RectangleTool"
        const val OutlinedRectangleToolIdentifier = "OutlinedRectangleTool"
        const val SquareToolIdentifier = "SquareTool"
        const val OutlinedSquareToolIdentifier = "OutlinedSquareTool"
        const val CircleToolIdentifier = "CircleTool"
        const val OutlinedCircleToolIdentifier = "OutlinedCircleTool"
        const val SprayToolIdentifier = "SprayTool"
        const val PolygonToolIdentifier = "PolygonTool"
        const val DitherToolIdentifier = "DitherTool"
        const val ShadingToolIdentifier = "ShadingTool"

        const val SharedPreferenceFirstLaunchIdentifier = "FirstLaunchIdentifier"
        const val SharedPreferencePixelPerfectIdentifier = "PixelPerfectSharedPreference"
        const val SharedPreferencesSprayRadiusIdentifier = "SprayRadiusSharedPreference"
        const val SharedPreferencesSprayStrengthIdentifier = "SprayStrengthSharedPreference"
        const val SharedPreferenceGridIdentifier = "GridSharedPreference"

        const val prevOrientationBundleIdentifier = "PrevOrientation"
        const val prevBitmapStrBundleIdentifier = "PrevBitmapStr"
        const val prevPrimaryColorBundleIdentifier = "PrevPrimaryColor"
        const val prevSecondaryColorBundleIdentifier = "PrevSecondaryColor"
        const val prevToolBundleIdentifier = "PrevTool"
        const val prevBrushBundleIdentifier = "PrevBrush"
        const val prevTabBundleIdentifier = "PrevTab"
        const val prevUndoStackJsonStrIdentifier = "prevUndoStackJsonStr"
        const val prevRedoStackJsonStrIdentifier = "prevRedoStackJsonStr"
        const val prevSymmetryModeStrIdentifier = "prevSymmetryModeStr"
    }

    object Colors {
        const val DefaultToolbarItemColor = "#0099cc"
        const val PixelGridViewCheckerboardColor = "#d9d9d9"
        const val SnackbarBackgroundColor = "#eaddff"
    }
}