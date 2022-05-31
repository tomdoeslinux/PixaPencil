package com.therealbluepandabear.pixapencil.listeners

interface NewProjectFragmentListener {
    fun onDoneButtonPressed(projectName: String, width: Int, height: Int, spotLightInProgress: Boolean)
}