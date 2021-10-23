package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var hasNavigatedBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        // TODO: In the future we can navigate using Jetpack Navigation
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)

            val asInt = Integer.parseInt(binding.spanCountEditText.text.toString())

            intent.putExtra("SPAN_COUNT", asInt)

            startActivity(intent)
        }

        setGreetingText()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        if (!hasNavigatedBack) {
            binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 3)
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

class RecentCreationsAdapter(private val list: List<Bitmap>) : RecyclerView.Adapter<RecentCreationsAdapter.ImageViewHolder>() {
    class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recent_creations_layout,
                parent,
                false
            ) as ImageView

        return ImageViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        for (bitmap in list) {
            holder.imageView.setImageBitmap(list[position])
        }
    }

    override fun getItemCount() = list.size


}

