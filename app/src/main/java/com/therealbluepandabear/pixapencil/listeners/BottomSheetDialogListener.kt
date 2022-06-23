package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.activities.main.BottomSheetDialog
import com.therealbluepandabear.pixapencil.models.PixelArt

interface BottomSheetDialogListener {
    fun onDuplicateTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog)
    fun onViewDetailsTapped(pixelArt: PixelArt)
    fun onRenameTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog)
    fun onDeleteTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog)
}