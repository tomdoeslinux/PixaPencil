package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.activities.main.binding

fun recentCreationsRecyclerViewAddOnScrollListener() {
    binding.activityMainRecentCreationsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy == 1 || dy == 0) {
                return
            }

            if (binding.activityMainNewProjectButton.isShown && dy > 2 || dy < 2) {
                binding.activityMainNewProjectButton.hide()
            } else {
                binding.activityMainNewProjectButton.show()
            }
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) binding.activityMainNewProjectButton.show()
        }
    })
}