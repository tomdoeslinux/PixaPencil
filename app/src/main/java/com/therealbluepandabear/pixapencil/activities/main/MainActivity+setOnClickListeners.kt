package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.activities.main.eventlisteners.bottomNavigationViewSetOnItemSelectedListener
import com.therealbluepandabear.pixapencil.activities.main.eventlisteners.newProjectButtonSetOnClickListener
import com.therealbluepandabear.pixapencil.activities.main.eventlisteners.recentCreationsRecyclerViewAddOnScrollListener

fun MainActivity.setEventListeners() {
    binding.activityMainRecentCreationsRecyclerView.setHasFixedSize(true)
    bottomNavigationViewSetOnItemSelectedListener()
    recentCreationsRecyclerViewAddOnScrollListener()
    newProjectButtonSetOnClickListener()
}