package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.therealbluepandabear.pixapencil.extensions.changeColor
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.setMenuItemIcon(item: MenuItem, icon: Int, tooltipText: CharSequence? = item.tooltipText) {
    item.icon = ContextCompat.getDrawable(this, icon)
    item.tooltipText = tooltipText
    item.icon.changeColor(Color.parseColor(StringConstants.DefaultToolbarItemColor))
}
