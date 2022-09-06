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

import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.databinding.ProjectDetailsAlertBoxBinding
import com.therealbluepandabear.pixapencil.extensions.getNumberOfUniqueColors
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnViewDetailsTapped(pixelArt: PixelArt) {
    val detailsAlertBinding = ProjectDetailsAlertBoxBinding.inflate(LayoutInflater.from(this))

    detailsAlertBinding.projectDetailsAlertBoxWidth.text = pixelArt.width.toString()
    detailsAlertBinding.projectDetailsAlertBoxHeight.text = pixelArt.height.toString()
    detailsAlertBinding.projectDetailsAlertBoxColors.text = BitmapConverter.convertStringToBitmap(pixelArt.bitmap)?.getNumberOfUniqueColors().toString()
    detailsAlertBinding.projectDetailsAlertBoxCreated.text = pixelArt.dateCreated

    val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(R.string.dialog_project_details_title)
        .setView(detailsAlertBinding.root)
        .setPositiveButton(R.string.generic_ok, null)

    alertDialog.show()
}