package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.setPrimaryPixelColor
import com.therealbluepandabear.pixapencil.activities.canvas.setSecondaryPixelColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun setColors() {
    binding.activityCanvasColorPickerRecyclerView.viewTreeObserver.addOnGlobalLayoutListener {
        setPrimaryPixelColor(Color.BLACK)
        setSecondaryPixelColor(Color.BLUE)
    }
}