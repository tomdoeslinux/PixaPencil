package com.therealbluepandabear.pixapencil.activities.main.oncreate.root

import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.activities.main.*
import com.therealbluepandabear.pixapencil.activities.main.eventlisteners.setEventListeners
import com.therealbluepandabear.pixapencil.activities.main.oncreate.*
import com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider.addMenuProvider

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
    addMenuProvider()
    initActivityResultLauncher()
    registerOnBackPressedDispatcherCallback()
    selectedColorPaletteIndex = 0
}