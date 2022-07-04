package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun MainActivity.setEventListeners() {
    binding.activityMainRecentCreationsRecyclerView.setHasFixedSize(true)
    bottomNavigationViewSetOnItemSelectedListener()
    recentCreationsRecyclerViewAddOnScrollListener()
    newProjectButtonSetOnClickListener()
}