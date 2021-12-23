package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.newcolorpalette.NewColorPaletteFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.setMenuItemIcon(item: MenuItem, icon: Int, tooltipText: CharSequence? = item.tooltipText) {
    item.icon = ContextCompat.getDrawable(this, icon)
    item.tooltipText = tooltipText
    item.icon.colorFilter = PorterDuffColorFilter(Color.parseColor("#0099cc"), PorterDuff.Mode.SRC_IN)
}

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    val zoom = 0.8f
    val maxZoomOut = 0.19999999 // TODO - look into why Android is buggy when user zooms out past this number

    when (item.itemId) {
        R.id.zoom_out -> {
            binding.activityCanvasCanvasFragmentHostCardViewParent.apply {
                if (scaleX > maxZoomOut && scaleY > maxZoomOut) {
                    scaleX -= zoom
                    scaleY -= zoom
                }
            }
        }
        R.id.zoom_in -> {
            binding.activityCanvasCanvasFragmentHostCardViewParent.apply {
                scaleX += zoom
                scaleY += zoom
            }
        }
        R.id.save_project -> extendedSaveProject()

        R.id.undo -> extendedUndo()

        R.id.fullscreen -> {
            fullscreenEnabled = if (!fullscreenEnabled) {
                enableFullscreen()
                setMenuItemIcon(item, R.drawable.ic_baseline_fullscreen_exit_24, "Exit Fullscreen")
                true
            } else {
                disableFullscreen()
                setMenuItemIcon(item, R.drawable.ic_baseline_fullscreen_24, "Fullscreen")
                false
            }
        }
        R.id.new_color_palette -> {
            newColorPaletteFragmentInstance = NewColorPaletteFragment.newInstance()
            currentFragmentInstance = newColorPaletteFragmentInstance
            navigateTo(supportFragmentManager, newColorPaletteFragmentInstance, R.id.activityCanvas_primaryFragmentHost, StringConstants.FRAGMENT_NEW_COLOR_PALETTE_TITLE, binding.activityCanvasPrimaryFragmentHost, binding.activityCanvasRootLayout)
        }
    }
    return true
}