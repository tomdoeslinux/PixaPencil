package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Bitmap
import android.view.MenuItem
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.newcolorpalette.NewColorPaletteFragment
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

const val zoomIncrement = 0.2f

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {
        R.id.zoom_out -> {
            zoomOut()
        }
        R.id.zoom_in -> {
            zoomIn()
        }
        R.id.save_project -> {
            extendedSaveProject()
        }

        R.id.undo -> {
            extendedUndo()
        }

        R.id.redo -> {
            extendedRedo()
        }

        R.id.new_color_palette -> {
            newColorPaletteFragmentInstance = NewColorPaletteFragment.newInstance()
            currentFragmentInstance = newColorPaletteFragmentInstance
            navigateTo(supportFragmentManager, newColorPaletteFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FragmentNewColorPaletteTitle, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
            hideMenuItems()
        }

        R.id.pixel_perfect -> {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = !outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode

            menu.findItem(R.id.pixel_perfect).isChecked = outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode

            with (sharedPreferenceObject.edit()) {
                putBoolean(StringConstants.SharedPreferencePixelPerfectIdentifier, outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode)
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
            outerCanvasInstance.rotate(IntConstants.DegreesOneEighty)
        }

        R.id.rotate_90_degrees_anti_clockwise -> {
            outerCanvasInstance.rotate(IntConstants.DegreesNinety, animate = true, clockwise = false)
        }

        R.id.reset_zoom -> {
            resetZoom()
        }
    }
    return true
}