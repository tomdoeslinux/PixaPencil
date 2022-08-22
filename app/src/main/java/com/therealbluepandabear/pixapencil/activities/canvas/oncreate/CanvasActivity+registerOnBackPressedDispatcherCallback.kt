package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import android.content.Intent
import androidx.activity.OnBackPressedCallback
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.activities.canvas.showUnsavedChangesDialog
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun CanvasActivity.registerOnBackPressedDispatcherCallback() {
    onBackPressedDispatcher.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStackImmediate()

                    binding.root.post {
                        judgeUndoRedoStacks()
                    }
                } else if (!viewModel.saved) {
                    viewModel.unsavedChangesDialogShown = true
                    showUnsavedChangesDialog()
                } else {
                    startActivity(Intent(this@registerOnBackPressedDispatcherCallback, MainActivity::class.java))
                    finishAffinity()
                }
            }
        })
}