package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    showWelcomeScreenIfApplicable()
    setPrevOrientation()
    setVmPolicy()
    setBindings()
    setOnClickListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
}