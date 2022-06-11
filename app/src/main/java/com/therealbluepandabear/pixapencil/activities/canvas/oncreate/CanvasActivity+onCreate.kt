package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
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
    }
}