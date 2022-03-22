package com.realtomjoney.pyxlmoose.utility

/**
 * In the future, I will remove this to support different languages inside the app.
 *
 * Though for now, I will leave this file until I decide on adding support for more languages.
 */

object StringConstants {
    const val AppName = "PyxlMoose"

    const val TabStarredTitle = "Starred"

    const val ProjectTitleExtra = "PROJECT_TITLE"
    const val WidthExtra = "WIDTH"
    const val HeightExtra = "HEIGHT"

    const val IndexExtra = "INDEX"

    const val DialogPositiveButtonText = "OK"
    const val DialogNegativeButtonText = "Cancel"
    const val DialogExceptionInfoTitle = "Exception Info"
    const val DialogViewFileErrorTitle = "Error trying to view file"
    const val DialogClearCanvasTitle = "Clear canvas"
    const val DialogClearCanvasMessage = "Are you sure you want to clear the canvas?"
    const val DialogUnsavedChangesTitle = "Unsaved changes"
    const val DialogUnsavedChangesMessage = "You have unsaved changes, are you sure you want to exit?"

    const val SnackbarViewExceptionInfoButtonText = "View"
    const val SnackbarCannotDeletePrimaryColorPaletteText = "Cannot delete default color palette"
    const val SnackbarFindAndReplaceWarning = "You must have at least one color on your canvas to use this tool"

    const val FragmentFindAndReplaceTitle = "Find and Replace"
    const val FragmentColorPickerTitle = "Select Color"
    const val FragmentNewColorPaletteTitle = "New Color Palette"
    const val FragmentSprayToolSettingsTitle = "Spray Tool Settings"

    const val ColorFilterIdentifier = "COLOR_FILTER"
    const val DarkenFilterIdentifier = "DARKEN_FILTER"
    const val LightenFilterIdentifier = "LIGHTEN_FILTER"
    const val InvertFilterIdentifier = "INVERT_FILTER"
    const val GrayscaleFilterIdentifier = "GRAYSCALE_FILTER"

    const val PencilToolIdentifier = "PENCIL"
    const val FillToolIdentifier = "FILL"
    const val VerticalMirrorToolIdentifier = "VERTICAL_MIRROR"
    const val HorizontalMirrorToolIdentifier = "HORIZONTAL_MIRROR"
    const val LineToolIdentifier = "LINE"
    const val RectangleToolIdentifier = "RECTANGLE"
    const val OutlinedRectangleToolIdentifier = "OUTLINED_RECTANGLE"
    const val SquareToolIdentifier = "SQUARE"
    const val OutlinedSquareToolIdentifier = "OUTLINED_SQUARE"
    const val CircleToolIdentifier = "CIRCLE_TOOL"
    const val OutlinedCircleToolIdentifier = "OUTLINED_CIRCLE"
    const val SprayToolIdentifier = "SPRAY"
    const val PolygonToolIdentifier = "POLYGON"
    const val DitherToolIdentifier = "DITHER"
    const val DarkenToolIdentifier = "DARKEN"
    const val LightenToolIdentifier = "LIGHTEN"
    const val ClearCanvasToolIdentifier = "CLEAR_CANVAS"
    const val ColorPickerToolIdentifier = "COLOR_PICKER"
    const val FindAndReplaceToolIdentifier = "FIND_AND_REPLACE"
    const val EraseToolIdentifier = "ERASE"
    const val GridToolIdentifier = "GRID"

    const val SharedPreferencePixelPerfectIdentifier = "PIXEL_PERFECT"

    const val ExceptionInvalidWidthHeightMessage = "Invalid width/height value"
    const val ExceptionInvalidRadiusStrengthMessage = "Invalid radius/strength"
    const val ExceptionAccessingNegativeIndex = "Cannot access pixel art object with a negative index in list!"

    var prevOrientationBundleIdentifier = "PrevOrientation"
    var prevBitmapStrBundleIdentifier = "PrevBitmapStr"

    const val DefaultToolbarItemColor = "#0099cc"
    const val PixelGridViewCheckerboardColor = "#d9d9d9"
    const val SnackbarBackgroundColor: String = "#eaddff"
}