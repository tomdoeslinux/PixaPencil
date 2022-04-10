package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.listeners.*
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.StringConstants

class CanvasActivity :
    AppCompatActivity(),
    CanvasFragmentListener,
    ColorPickerListener,
    ColorPickerFragmentListener,
    FindAndReplaceFragmentListener,
    ToolsFragmentListener,
    FiltersFragmentListener,
    ColorPalettesFragmentListener,
    NewColorPaletteFragmentListener,
    BrushesFragmentListener,
    SprayToolSettingsFragmentListener {

    var previousView: View? = null

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        configureSavedInstanceState(savedInstanceState)
    }

    fun initColorPickerFragmentInstance(colorPaletteMode: Boolean): ColorPickerFragment {
        return ColorPickerFragment.newInstance(getSelectedColor(), colorPaletteMode)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return extendedOnCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return extendedOnOptionsItemSelected(item)
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onResume() {
        extendedOnResume()
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        extendedOnSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onPixelTapped(coordinatesTapped: Coordinates) {
        extendedOnPixelTapped(coordinatesTapped)
    }

    override fun onActionUp() {
        extendedOnActionUp()
    }

    override fun onRedoActionCompleted(undoStack: List<BitmapAction>) {
        extendedOnRedoActionCompleted(undoStack)
    }

    override fun onUndoActionCompleted(
        undoStack: List<BitmapAction>,
        bitmapActionData: List<BitmapAction>
    ) {
        extendedOnUndoActionCompleted(undoStack, bitmapActionData)
    }

    override fun onColorTapped(colorTapped: Int, view: View) {
        extendedOnColorTapped(colorTapped, view)
    }

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        extendedOnColorLongTapped(colorPalette, colorIndex)
    }

    override fun onColorAdded(colorPalette: ColorPalette) {
        extendedOnAddColorTapped(colorPalette)
    }

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) {
        extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)
    }

    override fun onColorToFindTapped(bitmap: Bitmap, colorToFind: Int): Bitmap {
        return extendedOnColorToFindTapped(bitmap, colorToFind)
    }

    override fun onColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap {
        return extendedOnColorToReplaceTapped(bitmap, colorToReplace)
    }

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
        extendedOnDoneButtonPressed(colorToFind, colorToReplace)
    }

    override fun onBackPressed() {
        extendedOnBackPressed()
    }

    override fun onToolTapped(toolName: String) {
        extendedOnToolTapped(toolName)
    }

    override fun onToolLongTapped(toolName: String) {
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

    override fun onFilterTapped(filterName: String) {
        extendedOnFilterSelected(filterName)
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteTapped(selectedColorPalette)
    }

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
        extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)
    }

    override fun onBrushTapped(selectedBrush: Brush) {
        extendedOnBrushTapped(selectedBrush)
    }

    override fun onDoneButtonPressed(radius: String, strength: String) {
        extendedOnDoneButtonPressed(radius, strength)
    }
}
