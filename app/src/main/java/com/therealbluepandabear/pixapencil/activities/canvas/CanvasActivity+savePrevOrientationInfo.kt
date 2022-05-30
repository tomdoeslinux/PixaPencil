package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.extensions.isEmpty
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.savePrevOrientationInfo() {

    if (prevOrientation != resources.configuration.orientation) {
        lifecycleScope.launch {
            delay(200)
            onOrientationChanged()
            delay(200)

            for (fragment in supportFragmentManager.fragments) {
                if (fragment is ActivityFragment) {
                    title = fragment.title
                }
            }

            if (prevPrimaryColor != null && prevSecondaryColor != null) {
                setPrimaryPixelColor(prevPrimaryColor!!)
                setSecondaryPixelColor(prevSecondaryColor!!)
            }

            if (prevToolStr != null) {
                currentTool = if (Tool.findToolByName(prevToolStr!!) != null) {
                    Tool.findToolByName(prevToolStr!!)!!
                } else {
                    Tool.PencilTool
                }

                toolsFragmentInstance?.tapOnToolByName(prevToolStr!!)
            }

            if (prevBrushStr != null) {
                pixelGridViewInstance.currentBrush =
                    BrushesDatabase.toList().find { it.brushName == prevBrushStr }
            }

            if (prevTab != 0) {
                binding.activityCanvasTabLayout.getTabAt(prevTab)?.select()
            }

            if (prevUndoToolbarButtonDisabledEnabledState && menu.findItem(R.id.activityCanvasTopAppMenu_undo) != null) {
                menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
            }

            if (prevRedoToolbarButtonDisabledEnabledState && menu.findItem(R.id.activityCanvasTopAppMenu_redo_item) != null) {
                menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
            }

            if (prevSymmetryModeStr != null) {
                pixelGridViewInstance.symmetryMode =
                    SymmetryMode.values().first { it.symmetryName == prevSymmetryModeStr }

                when (pixelGridViewInstance.symmetryMode) {
                    SymmetryMode.Horizontal -> {
                        menu.findItem(R.id.appMenu_symmetry_horizontal_subItem).isChecked = true
                    }

                    SymmetryMode.Vertical -> {
                        menu.findItem(R.id.appMenu_symmetry_vertical_subItem).isChecked = true
                    }

                    SymmetryMode.Quad -> {
                        menu.findItem(R.id.appMenu_symmetry_quad_subItem).isChecked = true
                    }

                    else -> {}
                }
            }

            if (prevRotation != 0) {
                outerCanvasInstance.rotate(prevRotation)
            }

            replacedBMP = true

            val viewTemp = binding.activityCanvasOuterCanvasFragmentHost

            val fileHelperUtilitiesInstance =
                FileHelperUtilities.createInstance(this@savePrevOrientationInfo)
            val convertedBMP = prevBitmapFilePathStr?.let {
                fileHelperUtilitiesInstance.openBitmapFromInternalStorage(
                    it
                )
            }

            if (convertedBMP?.isEmpty() == false) {
                viewTemp.viewTreeObserver.addOnGlobalLayoutListener {
                    if (replacedBMP) {
                        pixelGridViewInstance.replaceBitmap(convertedBMP)
                        fileHelperUtilitiesInstance.deleteBitmapFromInternalStorage(
                            prevBitmapFilePathStr!!
                        )

                        replacedBMP = false

                        prevOrientation = resources.configuration.orientation
                    }
                }
            }
        }
    }
}