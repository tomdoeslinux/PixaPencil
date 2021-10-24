package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hasNavigatedBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            intent.putExtra("SPAN_COUNT", Integer.parseInt(binding.spanCountEditText.text.toString()))

            startActivity(intent)
        }
        setGreetingText()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        if (!hasNavigatedBack) {
            binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 4)
            binding.recentCreationsRecyclerView.adapter =
                RecentCreationsAdapter(BitmapDatabase.toList())
        } else {
            binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()
        }
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

