package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnCreationLongTapped(pixelArt: PixelArt) {
    BottomSheetDialog.newInstance(pixelArt).show(supportFragmentManager, "bottomSheet")
}