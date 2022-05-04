package com.therealbluepandabear.pixapencil.activities.canvas

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun CanvasActivity.extendedOnResume() {
}
