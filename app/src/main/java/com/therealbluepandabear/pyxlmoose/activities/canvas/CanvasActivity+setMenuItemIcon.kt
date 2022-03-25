package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.graphics.Color
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.therealbluepandabear.pyxlmoose.extensions.changeColor
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun CanvasActivity.setMenuItemIcon(item: MenuItem, icon: Int, tooltipText: CharSequence? = item.tooltipText) {
    item.icon = ContextCompat.getDrawable(this, icon)
    item.tooltipText = tooltipText
    item.icon.changeColor(Color.parseColor(StringConstants.DefaultToolbarItemColor))
}
