package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.activities.main.eventlisteners.setEventListeners

fun MainActivity.extendedOnCreate() {
    showWelcomeScreenIfApplicable()
    setVmPolicy()
    setBindings()
    setEventListeners()
    initializeRoomDatabases()
    requestPermissions()
    applyShowLargeCanvasSizeWarningValueFromPreference()
    setupRecyclerView()
    observePixelArtData()
    selectedColorPaletteIndex = 0
}