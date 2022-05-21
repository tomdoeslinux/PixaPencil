package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.height
import com.therealbluepandabear.pixapencil.activities.canvas.pixelArtId
import com.therealbluepandabear.pixapencil.activities.canvas.width
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment

fun CanvasActivity.setUpFragment() {
    outerCanvasInstance = OuterCanvasFragment.newInstance(width, height, projectTitle, pixelArtId)

    supportFragmentManager.commit {
        add(
            R.id.activityCanvas_outerCanvasFragmentHost,
            outerCanvasInstance)
    }
}