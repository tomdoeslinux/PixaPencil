package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.hideItems
import com.therealbluepandabear.pixapencil.extensions.navigateTo
import com.therealbluepandabear.pixapencil.fragments.spraytoolsettings.SprayToolSettingsFragment

fun CanvasActivity.sprayToolOnToolTapped() {
    if (currentTool == Tools.SprayTool) {
        sprayToolSettingsFragmentInstance =
            SprayToolSettingsFragment.newInstance()
        currentFragmentInstance = sprayToolSettingsFragmentInstance

        navigateTo(
            supportFragmentManager,
            sprayToolSettingsFragmentInstance,
            R.id.activityCanvas_primaryFragmentHost,
            getString(R.string.fragment_spray_tool_settings_title_in_code_str),
            binding.activityCanvasPrimaryFragmentHost,
            binding.activityCanvasRootLayout
        )

        menu.hideItems()
    }

    currentTool = Tools.SprayTool
}