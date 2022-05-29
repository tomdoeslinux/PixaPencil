package com.therealbluepandabear.pixapencil.activities.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

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