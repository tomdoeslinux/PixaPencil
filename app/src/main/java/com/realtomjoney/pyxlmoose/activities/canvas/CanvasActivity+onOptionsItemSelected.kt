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
    val zoomIncrement = 0.2f

    when (item.itemId) {
        R.id.zoom_out -> {
            binding.activityCanvasCanvasFragmentHostCardViewParent.apply {
                scaleX -= zoomIncrement
                scaleY -= zoomIncrement
            }
        }
        R.id.zoom_in -> {
            binding.activityCanvasCanvasFragmentHostCardViewParent.apply {
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
        }

        R.id.pixel_perfect -> {
            canvasInstance.myCanvasViewInstance.pixelPerfectMode = !canvasInstance.myCanvasViewInstance.pixelPerfectMode

            menu.findItem(R.id.pixel_perfect).isChecked =  canvasInstance.myCanvasViewInstance.pixelPerfectMode
        }
    }
    return true
}