package com.therealbluepandabear.pixapencil.activities.main.bottomsheet

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.BottomSheetDialog
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnDeleteTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
    bottomSheetDialog.dismiss()
    pixelArtViewModel.delete(pixelArt)

    binding.activityMainCoordinatorLayout.showSnackbarWithAction(
        getString(R.string.dialog_delete_pixel_art_project_deleted_text_in_code_str, pixelArt.title),
        SnackbarDuration.Long,
        getString(R.string.activityCanvasTopAppMenu_undo_str)) {
        pixelArtViewModel.insert(pixelArt)
    }
}