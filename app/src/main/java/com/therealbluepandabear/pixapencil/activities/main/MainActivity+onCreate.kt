package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex

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
    selectedColorPaletteIndex = 0
}