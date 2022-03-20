package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.setMenuItemIcon(item: MenuItem, icon: Int, tooltipText: CharSequence? = item.tooltipText) {
    item.icon = ContextCompat.getDrawable(this, icon)
    item.tooltipText = tooltipText
    item.icon.colorFilter = PorterDuffColorFilter(Color.parseColor(StringConstants.DefaultToolbarItemColor), PorterDuff.Mode.SRC_IN)
}
