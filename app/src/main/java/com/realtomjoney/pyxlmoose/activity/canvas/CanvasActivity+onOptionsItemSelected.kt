package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.realtomjoney.pyxlmoose.CanvasFragment
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.StringValues
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

fun extendedOnOptionsItemSelected(item: MenuItem, binding: ActivityCanvasBinding, context: CanvasActivity, data: List<View>): Boolean {
    val zoom = 0.3f
    when (item.itemId) {
        R.id.zoom_out -> {
            binding.fragmentHost.scaleX -= zoom; binding.fragmentHost.scaleY -= zoom
        }
        R.id.zoom_in -> {
            binding.fragmentHost.scaleX += zoom; binding.fragmentHost.scaleY += zoom
        }
        R.id.pixel_grid_on_off -> {
            if (pixelGridOn) {
                context.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, false, data)).commit()
                item.icon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_grid_on_24)
                item.title = StringValues.GRID_ON

                pixelGridOn = false

            } else if (!pixelGridOn) {
                context.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, true, data)).commit()
                item.icon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_grid_off_24)
                item.title = StringValues.GRID_OFF

                pixelGridOn = true
            }

        }

    }; return true
}