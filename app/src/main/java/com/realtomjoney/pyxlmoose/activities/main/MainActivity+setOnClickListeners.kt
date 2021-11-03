package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar


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
        val dialogueEditText = EditText(this)
        dialogueEditText.hint = "Span count"
        showDialog(
            "Span count",
            "Please input an appropriate span count value between 1 and 100:",
            "Done",
            { _, _ ->
                try {
                    if (Integer.parseInt(dialogueEditText.text.toString()) in 1..100) {
                        startActivity(
                            Intent(this, CanvasActivity::class.java).putExtra(
                                "SPAN_COUNT", Integer.parseInt(dialogueEditText.text.toString())
                            )
                        )
                    }
                } catch (ex: Exception) {
                    binding.mainRoot.showSnackbar(ex.toString(), SnackbarDuration.DEFAULT)
                }
            }, "Back", { _, _ -> }, dialogueEditText)


    }
}