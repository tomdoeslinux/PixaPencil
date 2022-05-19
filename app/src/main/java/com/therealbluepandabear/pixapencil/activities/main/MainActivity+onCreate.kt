package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    initSharedPreferencesObject()
    showWelcomeScreenIfApplicable()
    setPrevOrientation()
    setVmPolicy()
    setBindings()
    setEventListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
    applyShowLargeCanvasSizeWarningValueFromPreference()
    initView()
    observeData()
}