package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.utility.StringValues

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {
    val zoom = 0.3f
    when (item.itemId) {
        R.id.zoom_out -> {
            binding.activityCanvasCanvasFragmentHost.scaleX -= zoom; binding.activityCanvasCanvasFragmentHost.scaleY -= zoom
        }
        R.id.zoom_in -> {
            binding.activityCanvasCanvasFragmentHost.scaleX += zoom; binding.activityCanvasCanvasFragmentHost.scaleY += zoom
        }
        R.id.pixel_grid_on_off -> {
            if (pixelGridOn) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityCanvas_canvasFragmentHost, CanvasFragment.newInstance(spanCount, false, data)).commit()
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_on_24)
                item.title = StringValues.GRID_ON

                pixelGridOn = false

            } else if (!pixelGridOn) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activityCanvas_canvasFragmentHost, CanvasFragment.newInstance(spanCount, true, data)).commit()
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_off_24)
                item.title = StringValues.GRID_OFF

                pixelGridOn = true
            }

        }

    }; return true
}