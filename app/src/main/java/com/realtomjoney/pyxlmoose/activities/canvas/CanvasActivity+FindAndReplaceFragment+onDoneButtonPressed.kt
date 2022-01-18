package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    canvasInstance.myCanvasViewInstance.replacePixelsByColor(colorToFind!!, colorToReplace!!)

    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)
    setUpRecyclerView()
}