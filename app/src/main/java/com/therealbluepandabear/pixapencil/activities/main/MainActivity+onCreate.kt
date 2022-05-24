package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    showWelcomeScreenIfApplicable()
    setVmPolicy()
    setBindings()
    setEventListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
    applyShowLargeCanvasSizeWarningValueFromPreference()
    initView()
    observePixelArtData()
}