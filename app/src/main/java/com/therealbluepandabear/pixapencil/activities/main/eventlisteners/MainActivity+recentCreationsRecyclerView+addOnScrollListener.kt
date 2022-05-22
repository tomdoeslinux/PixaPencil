package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

private var scrollingDown = false

fun MainActivity.recentCreationsRecyclerViewAddOnScrollListener() {
    binding.activityMainRecentCreationsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val threshold = 7

            if (dy < -threshold) {
                scrollingDown = false
                binding.activityMainNewProjectButton.show()
            } else if (dy > threshold) {
                scrollingDown = true
                binding.activityMainNewProjectButton.hide()
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE && !scrollingDown) {
                binding.activityMainNewProjectButton.show()
            }
        }
    })
}