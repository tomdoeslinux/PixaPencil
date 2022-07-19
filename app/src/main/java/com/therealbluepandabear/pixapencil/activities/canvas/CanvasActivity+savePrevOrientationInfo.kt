package com.therealbluepandabear.pixapencil.activities.canvas

import android.view.ViewTreeObserver
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.rotate
import com.therealbluepandabear.pixapencil.extensions.isEmpty
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import kotlinx.coroutines.launch

fun CanvasActivity.savePrevOrientationInfo() {

    if (prevOrientation != resources.configuration.orientation) {
        lifecycleScope.launch {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment is ActivityFragment) {
                    title = fragment.title
                }
            }

            if (prevRotation != 0) {
                rotate(prevRotation)
            }

            replacedBMP = true

            val viewTemp = binding.activityCanvasCardView

            val fileHelperUtilitiesInstance = FileHelperUtilities.createInstance(this@savePrevOrientationInfo)
            val convertedBMP = prevBitmapFilePathStr?.let {
                fileHelperUtilitiesInstance.openBitmapFromInternalStorage(
                    it
                )
            }

            if (convertedBMP?.isEmpty() == false && viewModel.bitmapActionData.isNotEmpty()) {
                viewTemp.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        if (replacedBMP)
                        {
                            binding.activityCanvasPixelGridView.replaceBitmap(convertedBMP)
                            fileHelperUtilitiesInstance.deleteBitmapFromInternalStorage(
                                prevBitmapFilePathStr!!
                            )

                            replacedBMP = false

                            prevOrientation = resources.configuration.orientation
                            viewTemp.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    }
                })
            }
        }
    }
}