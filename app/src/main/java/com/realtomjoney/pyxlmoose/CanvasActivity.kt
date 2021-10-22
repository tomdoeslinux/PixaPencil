package com.realtomjoney.pyxlmoose

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

var colour: Int = Color.BLACK

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener {
    private lateinit var binding: ActivityCanvasBinding

    var data = listOf<Pixel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setUpFragment()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.colourPickerRecyclerView.layoutManager = layoutManager

        binding.colourPickerRecyclerView.adapter = MyAdapter(listOf(
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.BLACK, Color.argb(100, 255, 69, 0)), binding.rootLayout)
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
            val px = Pixel(this)
            list.add(px)
        }
        data = list
        return list.toList()
    }

    override fun onPixelTapped(pixel: Pixel) {
        try {
            pixel.setBackgroundColor(colour)
        } catch (e: Exception) {

        }
    }
}

class MyAdapter(private val list: List<Int>,
                private val rootLayout: View) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.colour_picker_layout,
                parent,
                false
            ) as View

        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.setBackgroundColor(list[position])

        holder.view.setOnClickListener {
            colour = list[position]
        }
    }

    override fun getItemCount() = list.size
}

