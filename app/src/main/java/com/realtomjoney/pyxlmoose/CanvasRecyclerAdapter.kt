package com.realtomjoney.pyxlmoose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CanvasRecyclerAdapter(private val pixels: List<Pixel>,
                            private val caller: CanvasFragmentListener) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentPixel = pixels[position]
        holder.tileParent.addView(currentPixel)

        holder.tileParent.setOnClickListener {
            caller.onPixelTapped(currentPixel)
        }
    }

    override fun getItemCount() = pixels.size
}