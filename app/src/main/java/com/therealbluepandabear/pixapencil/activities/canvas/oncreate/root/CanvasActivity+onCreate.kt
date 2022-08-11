package com.therealbluepandabear.pixapencil.activities.canvas.oncreate.root

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.flip
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.rotate
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.setOnTouchListener
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.*
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu.addMenuProvider
import com.therealbluepandabear.pixapencil.activities.canvas.showUnsavedChangesDialog
import com.therealbluepandabear.pixapencil.activities.canvas.startSpotLight
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun CanvasActivity.onCreate() {
    viewModel.currentBitmapAction = null
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setupBindings()
    setupRecyclerView()
    setupEventListeners()
    setupSharedPreferenceObject()
    setupViewPager()
    addMenuProvider()
    calcCrucialViewDimensions()

    binding.activityCanvasColorPickerRecyclerView.doOnPreDraw {
        observeColorPaletteColorPickerData()
    }

    originalX = null
    originalY = null

    binding.root.post {
        rotate(viewModel.currentRotation)
        for (i in viewModel.flipMatrix) {
            flip(i)
        }

        binding.activityCanvasColorSwitcherView.setPrimaryColor(viewModel.primaryColor)
        binding.activityCanvasColorSwitcherView.setSecondaryColor(viewModel.secondaryColor)

        binding.activityCanvasColorSwitcherView.setIsPrimarySelected(viewModel.isPrimaryColorSelected)

        lifecycleScope.launch {
            delay(400)
            if (viewModel.undoStack.isNotEmpty() && menu.findItem(R.id.activityCanvasTopAppMenu_undo) != null) {
                menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
            }

            if (viewModel.redoStack.isNotEmpty() && menu.findItem(R.id.activityCanvasTopAppMenu_redo_item) != null) {
                menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
            }
        }

        supportFragmentManager.findFragmentByTag("f" + 0)?.requireView()?.doOnPreDraw {
            (supportFragmentManager.findFragmentByTag("f" + 0) as ToolsFragment).tapOnToolByName(viewModel.currentTool.toolName)
        }

        binding.activityCanvasTabLayout.getTabAt(binding.activityCanvasViewPager2.currentItem)?.select()

        if (viewModel.currentTool.toolName == StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER) {
            lifecycleScope.launch {
                delay(200)
                setOnTouchListener()
            }
        }

        if (viewModel.unsavedChangesDialogShown) {
            showUnsavedChangesDialog()
        }
    }

    binding.root.doOnNextLayout {
        if (spotLightInProgress) {
            startSpotLight()
        }
    }
}