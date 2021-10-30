package com.realtomjoney.pyxlmoose.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.activity.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecentCreationsListener {

    lateinit var binding: ActivityMainBinding
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

    private fun setOnClickListeners() = extendedSetOnClickListeners()

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
        startActivity(Intent(this, CanvasActivity::class.java).putExtra("INDEX", PixelArtDatabase.toList()
            .indexOf(param)))

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
                    PixelArtDatabase.toList().indexOf((param)))
            }
    }
}

