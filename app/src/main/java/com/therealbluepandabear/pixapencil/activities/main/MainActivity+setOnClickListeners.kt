package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.setEventListeners() {
    recentCreationsRecyclerViewSetHasFixedSize()
    bottomNavigationViewSetOnItemSelectedListener()
    recentCreationsRecyclerViewAddOnScrollListener()
    newProjectButtonSetOnClickListener()
}