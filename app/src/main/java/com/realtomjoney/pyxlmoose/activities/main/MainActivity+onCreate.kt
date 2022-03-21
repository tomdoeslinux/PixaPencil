package com.realtomjoney.pyxlmoose.activities.main

fun MainActivity.extendedOnCreate() {
    setVmPolicy()
    setBindings()
    setOnClickListeners()
    setTitle()
    initializeRoomDatabases()
    requestPermissions()
}