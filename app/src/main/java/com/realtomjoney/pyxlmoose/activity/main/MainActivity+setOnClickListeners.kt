package com.realtomjoney.pyxlmoose.activity.main

import android.content.Intent
import android.graphics.Color
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.activity.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.*


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
                if (Integer.parseInt(dialogueEditText.text.toString()) in 1..100) {
                    startActivity(
                        Intent(this, CanvasActivity::class.java).putExtra(
                            "SPAN_COUNT", Integer.parseInt(dialogueEditText.text.toString())
                        )
                    )
                }
            }, "Back", { _, _ -> }, dialogueEditText)


    }
}