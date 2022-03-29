package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.extendedOnCreate() {
    setVmPolicy()
    setBindings()
    setOnClickListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
}