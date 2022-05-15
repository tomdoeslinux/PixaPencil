package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.*
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.initSharedPreferenceObject

fun CanvasActivity.onCreate() {
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    initSharedPreferenceObject()
    setObjectGlobalScopeLifecycleOwner()
    setFlags()
}