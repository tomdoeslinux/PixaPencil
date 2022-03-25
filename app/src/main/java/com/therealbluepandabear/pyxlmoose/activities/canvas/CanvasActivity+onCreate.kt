package com.therealbluepandabear.pyxlmoose.activities.canvas

fun CanvasActivity.onCreate() {
    getExtras()
    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()
    initSharedPreferenceObject()
    setObjectGlobalScopeLifecycleOwner()
}