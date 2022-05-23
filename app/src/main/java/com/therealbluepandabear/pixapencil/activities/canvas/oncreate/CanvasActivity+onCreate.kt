package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.utility.IntConstants

fun CanvasActivity.onCreate() {
    prevOrientation = resources.configuration.orientation
    viewModel.currentBitmapAction = null
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setUpFragment()
    setBindings()
    IntConstants.ActivityCanvasRootLayoutMeasuredHeightReference = binding.root.measuredHeight
    IntConstants.ActivityCanvasRootLayoutMeasuredWidthReference = binding.root.measuredWidth
    setUpRecyclerView()
    setOnClickListeners()
    initSharedPreferenceObject()
    setObjectGlobalScopeLifecycleOwner()
    setFlags()
    observeColorPaletteColorPickerData()
}