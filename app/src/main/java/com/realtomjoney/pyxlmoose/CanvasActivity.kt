package com.realtomjoney.pyxlmoose

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener {
    private lateinit var binding: ActivityCanvasBinding

    private var colour: Int = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setUpFragment()
        setColourClickListeners()
    }

    private fun setColourClickListeners() {
        binding.colorRed.setOnClickListener {
            colour = (it.background as ColorDrawable).color
        }
        binding.colorGreen.setOnClickListener {
            colour = (it.background as ColorDrawable).color
        }
        binding.colorBlue.setOnClickListener {
            colour = (it.background as ColorDrawable).color
        }
        binding.colorPurple.setOnClickListener {
            colour = (it.background as ColorDrawable).color
        }
        binding.colorPink.setOnClickListener {
            colour = (it.background as ColorDrawable).color
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
        pixel.setBackgroundColor(colour)
    }
}