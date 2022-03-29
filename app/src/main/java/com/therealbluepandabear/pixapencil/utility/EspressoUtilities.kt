package com.therealbluepandabear.pixapencil.utility

import android.view.ViewGroup
import androidx.core.view.forEach

object EspressoUtilities {
    private fun getViewGroupsChildElementIds(viewGroup: ViewGroup): List<Int> {
        val list = mutableListOf<Int>()
        viewGroup.forEach {
            list.add(it.id)
        }
        return list
    }

    fun getActivityCanvasRootLayoutChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pixapencil.activities.canvas.binding.activityCanvasRootLayout)
    }

    fun getActivityMainRootLayoutChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pixapencil.activities.main.binding.mainRoot)
    }
    fun getNewCanvasFragmentChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pixapencil.fragments.newproject.binding_!!.fragmentNewCanvasRootLayout)
    }
    fun getColorPickerFragmentChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pixapencil.fragments.colorpicker.binding_!!.fragmentColorPickerRootLayout)
    }

    fun getFindAndReplaceFragmentChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pixapencil.fragments.findandreplace.binding_!!.findAndReplaceFragmentRootLayout)
    }
}