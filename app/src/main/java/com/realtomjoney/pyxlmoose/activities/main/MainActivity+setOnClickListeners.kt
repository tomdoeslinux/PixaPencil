package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.fragments.NewCanvasFragment


fun MainActivity.extendedSetOnClickListeners() {
    binding.bottomNavigationView.setOnItemSelectedListener { item ->
        when(item.itemId) {
            R.id.page_home -> {
                binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(
                    PixelArtDatabase.toList(), this)
                title = "Home"
            }
            R.id.page_starred -> {
                binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(
                    PixelArtDatabase.toList().filter { it.isFavourited }, this)
                title = "Favorites"
            }
        }
        true
    }

    binding.recentCreationsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy == 1 || dy == 0) return

            if (binding.floatingActionButton.isShown && dy > 2 || dy < 2) binding.floatingActionButton.hide() else binding.floatingActionButton.show()
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) binding.floatingActionButton.show()
        }
    }) // Great solution by VelocityPulse (with a small twist from myself)

    binding.floatingActionButton.setOnClickListener {
        newCanvasFragmentInstance = NewCanvasFragment.newInstance()
        navigateTo(newCanvasFragmentInstance, R.id.newCanvasFragmentHost, "New Canvas", binding.newCanvasFragmentHost, binding.mainRoot)
    }
}