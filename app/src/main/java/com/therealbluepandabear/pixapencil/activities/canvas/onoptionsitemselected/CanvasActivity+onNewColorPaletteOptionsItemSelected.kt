package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.newcolorpalette.NewColorPaletteFragment

fun CanvasActivity.onNewColorPaletteOptionsItemSelected() {
    supportFragmentManager.commit {
        replace(
            R.id.activityCanvas_primaryFragmentHost,  NewColorPaletteFragment.newInstance())
        addToBackStack(null)
    }
}