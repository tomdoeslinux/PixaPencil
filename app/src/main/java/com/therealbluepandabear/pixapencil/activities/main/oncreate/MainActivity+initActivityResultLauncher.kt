package com.therealbluepandabear.pixapencil.activities.main.oncreate

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.putUriExtra
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import com.therealbluepandabear.pixapencil.utility.general.BitmapCompressFormatUtilities
import java.io.File

fun MainActivity.initActivityResultLauncher() {
    galleryActivityLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null) {
            val textInput: TextInputLayout =
                layoutInflater.inflate(R.layout.save_file_under_new_name_alert, findViewById(android.R.id.content),false) as TextInputLayout
            showDialog(
                "Enter name",
                "Enter a name for your project",
                getString(R.string.generic_ok), { _, _ ->
                    if (textInput.editText?.text?.isNotEmpty() == true) {
                        startActivity(
                            Intent(this, CanvasActivity::class.java)
                                .putExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA, textInput.editText?.text.toString())
                                .putExtra(StringConstants.Extras.SPOTLIGHT_IN_PROGRESS_EXTRA, false)
                                .putUriExtra(StringConstants.Extras.BITMAP_URI_EXTRA, it)
                        )
                    }
                },
                null, { _, _ -> }, textInput)
        }
    }
}