package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    setPrevOrientation()
    setVmPolicy()
    setBindings()
    setOnClickListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
}