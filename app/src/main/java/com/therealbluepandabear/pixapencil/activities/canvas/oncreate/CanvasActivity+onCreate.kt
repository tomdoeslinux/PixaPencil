package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.showUnsavedChangesDialog
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
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
    setListeners()
    initSharedPreferenceObject()
    setupViewPager()

    binding.activityCanvasColorPickerRecyclerView.doOnPreDraw {
        observeColorPaletteColorPickerData()
    }

    binding.root.post {
        lifecycleScope.launch {
            delay(400)
            if (viewModel.bitmapActionData.isNotEmpty() && menu.findItem(R.id.activityCanvasTopAppMenu_undo) != null) {
                menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
            }

            if (viewModel.undoStack.isNotEmpty() && menu.findItem(R.id.activityCanvasTopAppMenu_redo_item) != null) {
                menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
            }
        }

        supportFragmentManager.findFragmentByTag("f" + 0)?.requireView()?.doOnPreDraw {
            (supportFragmentManager.findFragmentByTag("f" + 0) as ToolsFragment).tapOnToolByName(viewModel.currentTool.toolName)
        }

        binding.activityCanvasTabLayout.getTabAt(binding.activityCanvasViewPager2.currentItem)?.select()

        if (viewModel.currentTool.toolName == StringConstants.Identifiers.MoveToolIdentifier) {
            lifecycleScope.launch {
                delay(200)
                outerCanvasInstance.setOnTouchListener()
            }
        }

        if (viewModel.unsavedChangesDialogShown) {
            showUnsavedChangesDialog()
        }
    }
}