package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    showWelcomeScreenIfApplicable()
    setVmPolicy()
    setBindings()
    setEventListeners()
    initializeRoomDatabases()
    requestPermissions()
    applyShowLargeCanvasSizeWarningValueFromPreference()
    initView()
    observePixelArtData()
}