package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.extensions.navigateTo
import com.therealbluepandabear.pyxlmoose.fragments.spraytoolsettings.SprayToolSettingsFragment
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

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