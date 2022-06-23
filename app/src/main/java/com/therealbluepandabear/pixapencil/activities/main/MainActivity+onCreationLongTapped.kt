package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnCreationLongTapped(pixelArt: PixelArt) {
    val bottomSheetDialog = BottomSheetDialog.newInstance(pixelArt)
    bottomSheetDialog.show(supportFragmentManager, "bottomSheet")
}