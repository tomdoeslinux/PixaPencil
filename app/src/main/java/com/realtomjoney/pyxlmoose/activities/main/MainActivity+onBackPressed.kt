package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun MainActivity.extendedOnBackPressed() {
    binding.activityMainNewProjectButton.show()
    navigateHome(supportFragmentManager, newCanvasFragmentInstance, binding.mainRoot, binding.newCanvasFragmentHost,"PyxlMoose")
}