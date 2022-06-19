package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.Intent
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialogWithNeutralButtonAndOnCancelListener
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.onCreate() {
    prevOrientation = resources.configuration.orientation
    viewModel.currentBitmapAction = null
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    initSharedPreferenceObject()

    binding.activityCanvasColorPickerRecyclerView.doOnPreDraw {
        observeColorPaletteColorPickerData()
    }

    binding.root.post {
        toolsFragmentInstance?.requireView()?.doOnPreDraw {
            toolsFragmentInstance?.tapOnToolByName(viewModel.currentTool.toolName)
        }

        if (viewModel.currentTab in 1..3) {
            binding.activityCanvasTabLayout.getTabAt(viewModel.currentTab)?.select()
        }

        if (viewModel.currentTool.toolName == StringConstants.Identifiers.MoveToolIdentifier) {
            lifecycleScope.launch {
                delay(200)
                outerCanvasInstance.setOnTouchListener()
            }
        }

        if (viewModel.unsavedChangesDialogShown) {
            showDialogWithNeutralButtonAndOnCancelListener(
                getString(R.string.dialog_unsaved_changes_title_in_code_str),
                getString(R.string.dialog_unsaved_changes_message_in_code_str, projectTitle),
                getString(R.string.generic_cancel_in_code_str),
                { _, _ ->
                    viewModel.unsavedChangesDialogShown = false
                },
                getString(R.string.dialog_unsaved_changes_negative_button_text_in_code_str),
                { _, _ ->
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                },
                getString(R.string.activityCanvasTopAppMenu_save_text_str),
                { _, _ ->
                    onSaveProjectOptionsItemSelected()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                },
                {
                    viewModel.unsavedChangesDialogShown = false
                }
            )
        }
    }
}