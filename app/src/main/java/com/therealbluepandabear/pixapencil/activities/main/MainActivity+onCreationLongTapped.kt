package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnCreationLongTapped(pixelArt: PixelArt) {
    bottomSheet = BottomSheetDialog(pixelArt)
    bottomSheet.show(supportFragmentManager, "bottomSheet")
}