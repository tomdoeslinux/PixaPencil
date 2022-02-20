package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Bitmap
import android.view.MenuItem
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.newcolorpalette.NewColorPaletteFragment
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    val zoomIncrement = 0.2f

    when (item.itemId) {
        R.id.zoom_out -> {
            outerCanvasInstance.cardViewParent.apply {
                if (outerCanvasInstance.cardViewParent.scaleX - zoomIncrement > zoomIncrement) {
                    scaleX -= zoomIncrement
                    scaleY -= zoomIncrement
                }
            }
        }
        R.id.zoom_in -> {
            outerCanvasInstance.cardViewParent.apply {
                scaleX += zoomIncrement
                scaleY += zoomIncrement
            }
        }
        R.id.save_project -> extendedSaveProject()

        R.id.undo -> extendedUndo()

        R.id.redo -> extendedRedo()

        R.id.new_color_palette -> {
            newColorPaletteFragmentInstance = NewColorPaletteFragment.newInstance()
            currentFragmentInstance = newColorPaletteFragmentInstance
            navigateTo(supportFragmentManager, newColorPaletteFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FRAGMENT_NEW_COLOR_PALETTE_TITLE, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
            hideMenuItems()
        }

        R.id.pixel_perfect -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = !outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode

            menu.findItem(R.id.pixel_perfect).isChecked = outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode

            with (sharedPreferenceObject.edit()) {
                putBoolean(StringConstants.SHARED_PREF_PIXEL_PERFECT, outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode)
                apply()
            }
        }

        R.id.export_to_png -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.saveAsImage(Bitmap.CompressFormat.PNG)
        }

        R.id.export_to_jpg -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.saveAsImage(Bitmap.CompressFormat.JPEG)
        }

        R.id.rotate_90_degrees_clockwise -> {
            outerCanvasInstance.rotate()
        }

        R.id.rotate_180_degrees_clockwise -> {
            outerCanvasInstance.rotate(IntConstants.DEGREES_ONE_EIGHTY)
        }

        R.id.rotate_90_degrees_anti_clockwise -> {
            outerCanvasInstance.rotate(IntConstants.DEGREES_NINETY, animate = true, clockwise = false)
        }


        R.id.reset_zoom -> {
            resetZoom()
        }
    }
    return true
}