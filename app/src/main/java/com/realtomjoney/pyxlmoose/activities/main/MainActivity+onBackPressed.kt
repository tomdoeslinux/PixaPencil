package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnBackPressed() {
    binding.activityMainNewProjectButton.show()
    navigateHome(supportFragmentManager, newCanvasFragmentInstance, binding.mainRoot, binding.activityMainPrimaryFragmentHost, StringConstants.APP_NAME)
}