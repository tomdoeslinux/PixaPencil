package com.realtomjoney.pyxlmoose.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.activity.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecentCreationsListener {

    lateinit var binding: ActivityMainBinding
    var hasNavigatedBack = false

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
        extendedOnResume()
        super.onResume()
    }

    private fun setBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreationTapped(param: PixelArt) =
        startActivity(Intent(this, CanvasActivity::class.java).putExtra("INDEX", PixelArtDatabase.toList()
            .indexOf(param)))

    fun refreshAdapter() {
        binding.recentCreationsRecyclerView.adapter = RecentCreationsAdapter(PixelArtDatabase.toList(), this)
    }

    override fun onCreationLongTapped(param: PixelArt) = extendedOnCreationLongTapped(param)
}

