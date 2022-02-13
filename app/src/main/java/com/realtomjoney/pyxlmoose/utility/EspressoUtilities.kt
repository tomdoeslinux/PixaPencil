package com.realtomjoney.pyxlmoose.utility

import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.extensions.doSomethingWithChildElements

object EspressoUtilities {
    private fun getViewGroupsChildElementIds(viewGroup: ViewGroup): List<Int> {
        val list = mutableListOf<Int>()
        viewGroup.doSomethingWithChildElements {
            list.add(it.id)
        }
        return list
    }

    fun getActivityCanvasRootLayoutChildElementIds() = getViewGroupsChildElementIds(com.realtomjoney.pyxlmoose.activities.canvas.binding.activityCanvasRootLayout)
    fun getActivityMainRootLayoutChildElementIds() = getViewGroupsChildElementIds(com.realtomjoney.pyxlmoose.activities.main.binding.mainRoot)
    fun getNewCanvasFragmentChildElementIds() = getViewGroupsChildElementIds(com.realtomjoney.pyxlmoose.fragments.newcanvas.binding_!!.fragmentNewCanvasRootLayout)
    fun getColorPickerFragmentChildElementIds() = getViewGroupsChildElementIds(com.realtomjoney.pyxlmoose.fragments.colorpicker.binding_!!.fragmentColorPickerRootLayout)
    fun getFindAndReplaceFragmentChildElementIds() = getViewGroupsChildElementIds(com.realtomjoney.pyxlmoose.fragments.findandreplace.binding_!!.findAndReplaceFragmentRootLayout)
}