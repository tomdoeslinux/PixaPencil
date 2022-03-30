package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.extensions.navigateHome
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MainActivity.extendedOnBackPressed() {
    binding.activityMainNewProjectButton.show()
    navigateHome(supportFragmentManager, newCanvasFragmentInstance, binding.mainRoot, binding.activityMainPrimaryFragmentHost, StringConstants.AppName)
    menu.showItems()
}