package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.MenuItem
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.newcolorpalette.NewColorPaletteFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    val zoomIncrement = 0.2f

    when (item.itemId) {
        R.id.zoom_out -> {
            outerCanvasInstance.cardViewParent.apply {
                if (outerCanvasInstance.cardViewParent.scaleX - zoomIncrement >= 0f) {
                    scaleX -= zoomIncrement
                    scaleY -= zoomIncrement
                }
            }
        }
        R.id.zoom_in -> {
            outerCanvasInstance.cardViewParent.apply {
                if (outerCanvasInstance.cardViewParent.scaleX + zoomIncrement >= 0f) {
                    scaleX += zoomIncrement
                    scaleY += zoomIncrement
                }
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
                putBoolean(StringConstants.SHARED_PREF_PIXEL_PIERFECT, outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode)
                apply()
            }
        }
    }
    return true
}