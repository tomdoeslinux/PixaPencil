package com.therealbluepandabear.pixapencil.activities.main.oncreate

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.hideSoftInput
import com.therealbluepandabear.pixapencil.extensions.putUriExtra
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.initActivityResultLauncher() {
    galleryActivityLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null) {
            val textInput: TextInputLayout =
                layoutInflater.inflate(R.layout.name_project_alert, findViewById(android.R.id.content),false) as TextInputLayout
            val text = textInput.editText?.text

            val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.dialog_open_image_enter_name_title)
                .setView(textInput)
                .setMessage(R.string.dialog_open_image_enter_name_text)
                .setPositiveButton(R.string.generic_ok) { _, _ ->
                    textInput.editText?.hideSoftInput()

                    if (text?.isNotEmpty() == true) {
                        startActivity(
                            Intent(this, CanvasActivity::class.java)
                                .putExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA, text.toString())
                                .putExtra(StringConstants.Extras.SPOTLIGHT_IN_PROGRESS_EXTRA, false)
                                .putUriExtra(StringConstants.Extras.BITMAP_URI_EXTRA, it)
                        )
                    }
                }
                .setNegativeButton(R.string.generic_cancel, null)

            alertDialog.show()
        }
    }
}