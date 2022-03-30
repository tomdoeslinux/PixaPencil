package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.extensions.navigateHome
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.navigateBack(fragmentInstance: Fragment) {
    currentFragmentInstance = null
    menu.showItems()
    navigateHome(
        supportFragmentManager,
        fragmentInstance,
        binding.activityCanvasRootLayout,
        binding.activityCanvasPrimaryFragmentHost,
        intent.getStringExtra(StringConstants.ProjectTitleExtra)!!)
    setUpRecyclerView()
    switchSelectedColorIndicator()
}