package com.therealbluepandabear.pyxlmoose.activities.main

fun MainActivity.extendedOnCreate() {
    setVmPolicy()
    setBindings()
    setOnClickListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
}