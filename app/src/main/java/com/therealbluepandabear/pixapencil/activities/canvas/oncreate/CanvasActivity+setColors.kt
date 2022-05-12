package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.setPrimaryPixelColor
import com.therealbluepandabear.pixapencil.activities.canvas.setSecondaryPixelColor

fun setColors() {
    binding.activityCanvasColorPickerRecyclerView.viewTreeObserver.addOnGlobalLayoutListener {
        setPrimaryPixelColor(Color.BLACK)
        setSecondaryPixelColor(Color.BLUE)
    }
}