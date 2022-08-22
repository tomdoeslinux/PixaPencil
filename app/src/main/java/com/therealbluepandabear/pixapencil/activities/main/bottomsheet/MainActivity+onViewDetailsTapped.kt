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