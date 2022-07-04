package com.therealbluepandabear.pixapencil.activities.main.bottomsheet

import com.google.android.material.textfield.TextInputLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.BottomSheetDialog
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnRenameTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
    val inflatedActivity = activity()?.layoutInflater?.inflate(R.layout.save_file_under_new_name_alert, activity()?.findViewById(android.R.id.content),false)
    val textInput: TextInputLayout = inflatedActivity as TextInputLayout

    showDialog(
        getString(R.string.dialog_rename_title_in_code_str),
        null,
        getString(R.string.generic_ok_in_code_str), { _, _ ->
            val input: String = textInput.editText?.text.toString()

            if (input.isNotBlank()) {
                pixelArt.title = input
                pixelArtViewModel.update(pixelArt)
                bottomSheetDialog.dismiss()
            }
        }, getString(R.string.generic_cancel_in_code_str), null, view = textInput, dimBackground = false
    )
}