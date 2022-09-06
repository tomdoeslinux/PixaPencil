/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

private var scrollingDown = false

fun MainActivity.recentCreationsRecyclerViewAddOnScrollListener() {
    binding.activityMainRecentCreationsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val threshold = 15

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