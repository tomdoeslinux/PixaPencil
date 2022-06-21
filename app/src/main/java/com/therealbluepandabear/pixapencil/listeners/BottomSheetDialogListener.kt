package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.PixelArt

interface BottomSheetDialogListener {
    fun onViewDetailsTapped(pixelArt: PixelArt)
    fun onRenameTapped(pixelArt: PixelArt)
    fun onDeleteTapped(pixelArt: PixelArt)
}