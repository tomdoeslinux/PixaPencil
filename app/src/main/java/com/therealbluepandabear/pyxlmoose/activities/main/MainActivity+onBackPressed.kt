package com.therealbluepandabear.pyxlmoose.activities.main

import com.therealbluepandabear.pyxlmoose.extensions.navigateHome
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnBackPressed() {
    binding.activityMainNewProjectButton.show()
    navigateHome(supportFragmentManager, newCanvasFragmentInstance, binding.mainRoot, binding.activityMainPrimaryFragmentHost, StringConstants.AppName)
}