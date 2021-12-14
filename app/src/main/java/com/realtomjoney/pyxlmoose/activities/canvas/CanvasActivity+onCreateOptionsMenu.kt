package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.view.Menu
import androidx.core.content.ContextCompat
import com.realtomjoney.pyxlmoose.R
import android.graphics.PorterDuff

import android.graphics.PorterDuffColorFilter




fun CanvasActivity.extendedOnCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.app_menu, menu)

    if (index != -1) {
        menu!!.getItem(2).icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_save_24)
        menu.getItem(2).icon.colorFilter = PorterDuffColorFilter(Color.parseColor("#0099cc"), PorterDuff.Mode.SRC_IN)
    }

    return true
}