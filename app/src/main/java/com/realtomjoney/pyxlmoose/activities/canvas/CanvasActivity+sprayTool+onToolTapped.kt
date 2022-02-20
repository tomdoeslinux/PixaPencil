package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.sprayToolOnToolTapped() {
    if (currentTool == Tools.SPRAY_TOOL) {
        sprayToolSettingsFragmentInstance =
            SprayToolSettingsFragment.newInstance()
        currentFragmentInstance = sprayToolSettingsFragmentInstance

        navigateTo(
            supportFragmentManager,
            sprayToolSettingsFragmentInstance,
            R.id.activityCanvas_primaryFragmentHost,
            StringConstants.FRAGMENT_SPRAY_TOOL_SETTINGS_TITLE,
            binding.activityCanvasPrimaryFragmentHost,
            binding.activityCanvasRootLayout
        )

        hideMenuItems()
    }

    currentTool = Tools.SPRAY_TOOL
}