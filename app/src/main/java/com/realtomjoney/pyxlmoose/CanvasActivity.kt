package com.realtomjoney.pyxlmoose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

class CanvasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCanvasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setUpFragment()
    }

    private fun setUpFragment() {
        val canvasFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHost, CanvasFragment.newInstance()).commit()
    }

    private fun setBindings() {
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}