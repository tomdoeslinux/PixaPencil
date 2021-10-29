package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), RecentCreationsListener {

    private lateinit var binding: ActivityMainBinding
    private var hasNavigatedBack = false

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        title = "Home"

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_home -> {
                    binding.recentCreationsRecyclerView.adapter =
                        RecentCreationsAdapter(PixelArtDatabase.toList(), this)
                    binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()
                    title = "Home"
                }
                R.id.page_starred -> {
                    val starred = PixelArtDatabase.toList().filter { it.isFavourited }
                    binding.recentCreationsRecyclerView.adapter =
                        RecentCreationsAdapter(starred, this)
                    binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()
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
        }) // Great solution by VelocityPulse

        binding.floatingActionButton.setOnClickListener {
            val dialogueEditText = EditText(this)
            dialogueEditText.hint = "Span count"

            val builder = MaterialAlertDialogBuilder(this)
                .setTitle("Span Count")
                .setMessage("Please input an appropriate span count value:")
                .setView(dialogueEditText)
                .setPositiveButton("Done") { _, _ ->
                    startActivity(
                        Intent(this, CanvasActivity::class.java)
                            .putExtra("SPAN_COUNT", Integer.parseInt(dialogueEditText.text.toString())))
                }
                .setNegativeButton("Back") { _, _ -> }
            builder.show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        binding.floatingActionButton.show()

        if (!hasNavigatedBack) {
            binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.recentCreationsRecyclerView.adapter =
                RecentCreationsAdapter(PixelArtDatabase.toList(), this)
        } else {
            binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()
        }

        super.onResume()
    }

    private fun setBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreationTapped(param: SavedPixelArt) {
        val intent = Intent(this, CanvasActivity::class.java)
        intent.putExtra("INDEX", PixelArtDatabase.toList().indexOf(param))
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreationLongTapped(param: SavedPixelArt) {
        val filtered = PixelArtDatabase.toList().filter { it != param }
        PixelArtDatabase.removeItem(PixelArtDatabase.toList().indexOf(param))
        binding.recentCreationsRecyclerView.adapter =
            RecentCreationsAdapter(filtered, this)
        binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()

        Snackbar.make(binding.recentCreationsRecyclerView,
            "You have deleted ${param.title}.",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor("#eaddff"))
            .show()
    }
}

