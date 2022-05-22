package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.ObjectConstants

fun CanvasActivity.onCreate() {
    prevOrientation = resources.configuration.orientation
    viewModel.currentBitmapAction = null
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setUpFragment()
    setBindings()
    IntConstants.ActivityCanvasRootLayoutMeasuredHeightReference = binding.activityCanvasRootLayout.measuredHeight
    IntConstants.ActivityCanvasRootLayoutMeasuredWidthReference = binding.activityCanvasRootLayout.measuredWidth
    setUpRecyclerView()
    setOnClickListeners()
    initSharedPreferenceObject()
    setObjectGlobalScopeLifecycleOwner()
    setFlags()
}