/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        getString(R.string.dialog_delete_pixel_art_project_deleted_text, pixelArt.title),
        SnackbarDuration.Long,
        getString(R.string.activityCanvasTopAppMenu_undo)) {
        pixelArtViewModel.insert(pixelArt)
    }
}