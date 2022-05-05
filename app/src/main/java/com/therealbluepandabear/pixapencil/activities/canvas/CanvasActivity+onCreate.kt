package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.onCreate() {
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()
    initSharedPreferenceObject()
    setObjectGlobalScopeLifecycleOwner()
    setFlags()
    savePrevOrientationInfo()
}