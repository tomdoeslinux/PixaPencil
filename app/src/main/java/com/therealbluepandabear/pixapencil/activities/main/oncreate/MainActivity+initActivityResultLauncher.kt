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

package com.therealbluepandabear.pixapencil.activities.main.oncreate

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.databinding.NameProjectAlertBinding
import com.therealbluepandabear.pixapencil.extensions.hideSoftInput
import com.therealbluepandabear.pixapencil.extensions.putUriExtra
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.initActivityResultLauncher() {
    galleryActivityLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null) {
            val nameProjectAlertBinding = NameProjectAlertBinding.inflate(LayoutInflater.from(this))

            nameProjectAlertBinding.root.post {
                // We do this so that there is no default top margin, which I personally find it ugly
                (nameProjectAlertBinding.root.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 0
            }

            val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.dialog_open_image_enter_name_title)
                .setView(nameProjectAlertBinding.root)
                .setPositiveButton(R.string.generic_ok) { _, _ ->
                    nameProjectAlertBinding.nameProjectAlertNameTextInputEditText.hideSoftInput()

                    if (nameProjectAlertBinding.nameProjectAlertNameTextInputEditText.text?.isNotEmpty() == true) {
                        startActivity(
                            Intent(this, CanvasActivity::class.java)
                                .putExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA, nameProjectAlertBinding.nameProjectAlertNameTextInputEditText.text.toString())
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