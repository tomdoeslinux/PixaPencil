package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RecentCreationsAdapter(private val list: List<Bitmap>) : RecyclerView.Adapter<RecentCreationsAdapter.ImageViewHolder>() {
    class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recent_creations_layout, parent, false) as ImageView)


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = list.forEach { _ ->
        holder.imageView.setImageBitmap(list[position])
    }

    override fun getItemCount() = list.size
}