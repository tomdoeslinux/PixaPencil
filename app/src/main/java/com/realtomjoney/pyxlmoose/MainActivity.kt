package com.realtomjoney.pyxlmoose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        // TODO: In the future we can navigate using Jetpack Navigation
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, CanvasActivity::class.java))
        }

        setGreetingText()
    }

    private fun setGreetingText() {
        binding.titleTextView.text = setGreeting()
    }

    private fun setGreeting(): String {
        val calendar = Calendar.getInstance()

        when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> return "Good Morning"
            in 12..21 -> return "Good Afternoon"
            in 21..24 -> return "Good Night"
        }
        return ""
    }

    private fun setBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}