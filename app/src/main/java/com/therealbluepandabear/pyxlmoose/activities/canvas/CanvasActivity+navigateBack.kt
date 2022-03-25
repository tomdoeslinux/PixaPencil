package com.therealbluepandabear.pyxlmoose.activities.canvas

import androidx.fragment.app.Fragment
import com.therealbluepandabear.pyxlmoose.extensions.navigateHome
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun CanvasActivity.navigateBack(fragmentInstance: Fragment) {
    currentFragmentInstance = null
    showMenuItems()
    navigateHome(
        supportFragmentManager,
        fragmentInstance,
        binding.activityCanvasRootLayout,
        binding.activityCanvasPrimaryFragmentHost,
        intent.getStringExtra(StringConstants.ProjectTitleExtra)!!)
    setUpRecyclerView()
    switchSelectedColorIndicator()
}