package com.therealbluepandabear.pyxlmoose.utility

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
        getViewGroupsChildElementIds(com.therealbluepandabear.pyxlmoose.activities.canvas.binding.activityCanvasRootLayout)
    }

    fun getActivityMainRootLayoutChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pyxlmoose.activities.main.binding.mainRoot)
    }
    fun getNewCanvasFragmentChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pyxlmoose.fragments.newcanvas.binding_!!.fragmentNewCanvasRootLayout)
    }
    fun getColorPickerFragmentChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pyxlmoose.fragments.colorpicker.binding_!!.fragmentColorPickerRootLayout)
    }

    fun getFindAndReplaceFragmentChildElementIds() {
        getViewGroupsChildElementIds(com.therealbluepandabear.pyxlmoose.fragments.findandreplace.binding_!!.findAndReplaceFragmentRootLayout)
    }
}