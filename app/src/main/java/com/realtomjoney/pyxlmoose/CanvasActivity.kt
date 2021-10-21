package com.realtomjoney.pyxlmoose

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener {
    private lateinit var binding: ActivityCanvasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setUpFragment()
    }

    private fun setColourClickListeners() {
        binding.colorRed.setOnClickListener {

        }
        binding.colorGreen.setOnClickListener {

        }
        binding.colorBlue.setOnClickListener {

        }
        binding.colorPurple.setOnClickListener {

        }
        binding.colorPink.setOnClickListener {

        }
    }

    private fun setUpFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHost, CanvasFragment.newInstance()).commit()
    }

    private fun setBindings() {
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initPixels(): List<Pixel> {
        val list = mutableListOf<Pixel>()
        for (i in 1..625) {
            list.add(Pixel(this))
        }
        return list.toList()
    }

    override fun onPixelTapped(pixel: Pixel) {
        pixel.setBackgroundColor(Color.BLACK)
    }
}