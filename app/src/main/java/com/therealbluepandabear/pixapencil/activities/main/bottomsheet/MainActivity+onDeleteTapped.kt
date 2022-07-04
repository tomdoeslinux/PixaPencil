package com.therealbluepandabear.pixapencil.activities.main.bottomsheet

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.BottomSheetDialog
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnDeleteTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
    showDialog(
        getString(R.string.dialog_delete_pixel_art_project_title_in_code_str, pixelArt.title),
        getString(R.string.dialog_delete_pixel_art_project_text_in_code_str, title),
        getString(R.string.generic_ok_in_code_str), { _, _ ->
            bottomSheetDialog.dismiss()
            pixelArtViewModel.delete(pixelArt)
        },  getString(R.string.generic_cancel_in_code_str), null, dimBackground = false
    )
}