package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.enums.Tools
import com.realtomjoney.pyxlmoose.extensions.navigateTo
import com.realtomjoney.pyxlmoose.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.sprayToolOnToolTapped() {
    if (currentTool == Tools.SprayTool) {
        sprayToolSettingsFragmentInstance =
            SprayToolSettingsFragment.newInstance()
        currentFragmentInstance = sprayToolSettingsFragmentInstance

        navigateTo(
            supportFragmentManager,
            sprayToolSettingsFragmentInstance,
            R.id.activityCanvas_primaryFragmentHost,
            StringConstants.FragmentSprayToolSettingsTitle,
            binding.activityCanvasPrimaryFragmentHost,
            binding.activityCanvasRootLayout
        )

        hideMenuItems()
    }

    currentTool = Tools.SprayTool
}