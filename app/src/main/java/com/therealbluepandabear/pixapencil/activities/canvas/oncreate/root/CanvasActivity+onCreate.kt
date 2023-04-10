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

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate.root

import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.*
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu.addMenuProvider
import com.therealbluepandabear.pixapencil.activities.canvas.showUnsavedChangesDialog
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun CanvasActivity.onCreate() {
    viewModel.currentBitmapAction = null
    initColorPalettesDBIfNotInitialized()
    getExtras()
    setupBindings()
    prepareDrawingView()
    registerOnBackPressedDispatcherCallback()
    setupRecyclerView()
    setupEventListeners()
    setupSharedPreferenceObject()
    setupViewPager()
    addMenuProvider()

    binding.activityCanvasColorPickerRecyclerView.doOnPreDraw {
        observeColorPaletteColorPickerData()
    }


    binding.root.post {
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
            (supportFragmentManager.findFragmentByTag("f" + 0) as ToolsFragment).tapOnToolByName(viewModel.currentTool.identifier)
        }

        binding.activityCanvasTabLayout.getTabAt(binding.activityCanvasViewPager2.currentItem)?.select()

        if (viewModel.unsavedChangesDialogShown) {
            showUnsavedChangesDialog()
        }
    }
}