package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MainActivity.extendedOnCreationLongTapped(pixelArt: PixelArt) {
    bottomSheet = BottomSheetDialog(pixelArt)
    bottomSheet.show(supportFragmentManager, "bottomSheet")
}