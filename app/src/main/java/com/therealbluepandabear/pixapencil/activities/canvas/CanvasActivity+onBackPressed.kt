package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.navigateHome
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb.RGBColorPickerFragment
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnBackPressed() {
    Flags.PressedBackToExit = true

    if (!saved && currentFragmentInstance == null) {
        showDialog(
            getString(R.string.dialog_unsaved_changes_title_in_code_str),
            getString(R.string.dialog_unsaved_changes_message_in_code_str),
            getString(R.string.dialog_positive_button_text_in_code_str),
            { _, _ ->
                startActivity(Intent(this, MainActivity::class.java))
            },  getString(R.string.dialog_negative_button_text_in_code_str), { _, _ -> }, null)
    } else if (currentFragmentInstance != null) {
        if (currentFragmentInstance is RGBColorPickerFragment) {
            (currentFragmentInstance as RGBColorPickerFragment).unregisterKeyboardVisiblityEventListenerRegistrar()
        }

        navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra(StringConstants.ProjectTitleExtra)!!)
        currentFragmentInstance = null
        menu.showItems()
        switchSelectedColorIndicator()
    } else {
        startActivity(Intent(this, MainActivity::class.java))
    }
}