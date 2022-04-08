package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.view.MenuItem
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.extensions.hideItems
import com.therealbluepandabear.pixapencil.extensions.navigateTo
import com.therealbluepandabear.pixapencil.fragments.newcolorpalette.NewColorPaletteFragment
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

const val zoomIncrement = 0.2f

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {
        R.id.activityCanvasTopAppMenu_zoom_out_item -> {
            zoomOut()
        }
        R.id.activityCanvasTopAppMenu_zoom_in_item -> {
            zoomIn()
        }
        R.id.activityMainTopAppMenu_community_item -> {
            extendedSaveProject()
        }

        R.id.activityCanvasTopAppMenu_undo -> {
            extendedUndo()
        }

        R.id.activityCanvasTopAppMenu_redo_item -> {
            extendedRedo()
        }

        R.id.activityCanvasTopAppMenu_new_color_palette_item -> {
            newColorPaletteFragmentInstance = NewColorPaletteFragment.newInstance()
            currentFragmentInstance = newColorPaletteFragmentInstance
            navigateTo(supportFragmentManager, newColorPaletteFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FragmentNewColorPaletteTitle, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
            menu.hideItems()
        }

        R.id.activityCanvasTopAppMenu_pixel_perfect_item -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelPerfectMode = !outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelPerfectMode

            menu.findItem(R.id.activityCanvasTopAppMenu_pixel_perfect_item).isChecked = outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelPerfectMode

            with (sharedPreferenceObject.edit()) {
                putBoolean(StringConstants.SharedPreferencePixelPerfectIdentifier, outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelPerfectMode)
                apply()
            }
        }

        R.id.activityCanvasTopAppMenu_export_to_png_item -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.saveAsImage(Bitmap.CompressFormat.PNG)
        }

        R.id.activityCanvasTopAppMenu_export_to_jpg_item -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.saveAsImage(Bitmap.CompressFormat.JPEG)
        }

        R.id.appMenu_rotate_90_degrees_clockwise_subItem -> {
            outerCanvasInstance.rotate()
        }

        R.id.appMenu_rotate_180_degrees_clockwise_subItem -> {
            outerCanvasInstance.rotate(IntConstants.DegreesOneEighty)
        }

        R.id.appMenu_rotate_90_degrees_anti_clockwise_subItem -> {
            outerCanvasInstance.rotate(IntConstants.DegreesNinety, animate = true, clockwise = false)
        }

        R.id.activityCanvasTopAppMenu_reset_zoom_subItem -> {
            resetZoom()
        }
    }
    return true
}