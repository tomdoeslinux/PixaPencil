package com.realtomjoney.pyxlmoose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecentCreationsListener {

    private lateinit var binding: ActivityMainBinding
    private var hasNavigatedBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setOnClickListeners()
        setTitle()
    }

    private fun setTitle() {
        title = "Home"
    }

    private fun setOnClickListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_home -> {
                    binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
                    title = "Home"
                }
                R.id.page_starred -> {
                    binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList().filter { it.isFavourited }, this)
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
                "Please input an appropriate span count value:",
                "Done",
                { _, _ -> startActivity(Intent(this, CanvasActivity::class.java).putExtra("SPAN_COUNT", Integer.parseInt(dialogueEditText.text.toString())))
                }, "Back", { _, _ -> }, dialogueEditText)
        }
    }

    override fun onResume() {
        binding.floatingActionButton.show()

        if (!hasNavigatedBack) {
            binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
        } else {
            binding.recentCreationsRecyclerView.adapter?.notifyItemInserted(ColourDatabase.toList().last())
        }

        super.onResume()
    }

    private fun setBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreationTapped(param: PixelArt) =
        startActivity(Intent(this, CanvasActivity::class.java).putExtra("INDEX", PixelArtDatabase.toList().indexOf(param)))

    private fun refreshAdapter() {
        binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
    }

    override fun onCreationLongTapped(param: PixelArt) {
        PixelArtDatabase.removeItem(param)
        refreshAdapter()
        binding.recentCreationsRecyclerView.adapter?.notifyItemRemoved(PixelArtDatabase.toList().indexOf((param)))

        (binding.recentCreationsRecyclerView)
            .showSnackbarWithAction("You have deleted ${param.title}", SnackbarDuration.DEFAULT, "Undo") {
                PixelArtDatabase.addItem(param)
                refreshAdapter()
                binding.recentCreationsRecyclerView.adapter?.notifyItemInserted(
                    PixelArtDatabase.toList().indexOf((param))
                )
            }
    }
}

