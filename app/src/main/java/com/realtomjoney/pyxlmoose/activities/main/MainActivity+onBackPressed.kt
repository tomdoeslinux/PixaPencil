package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.StringValues

fun MainActivity.extendedOnBackPressed() {
    binding.activityMainNewProjectButton.show()
    navigateHome(supportFragmentManager, newCanvasFragmentInstance, binding.mainRoot, binding.activityMainPrimaryFragmentHost, StringValues.APP_NAME)
}