package com.therealbluepandabear.pixapencil.activities.main.bottomsheet

import com.therealbluepandabear.pixapencil.activities.main.BottomSheetDialog
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnDuplicateTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
    val duplicatedPixelArt = PixelArt(
        pixelArt.coverBitmapFilePath,
        pixelArt.bitmap,
        pixelArt.width,
        pixelArt.height,
        pixelArt.title,
        pixelArt.starred
    )

    pixelArtViewModel.insert(duplicatedPixelArt)

    bottomSheetDialog.dismiss()
}