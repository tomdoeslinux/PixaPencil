package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    initSharedPreferencesObject()
    showWelcomeScreenIfApplicable()
    setPrevOrientation()
    setVmPolicy()
    setBindings()
    setOnClickListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
}