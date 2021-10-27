package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.constraintlayout.widget.ConstraintLayout


class RecentCreationsAdapter(private val data: List<SavedPixelArt>) : RecyclerView.Adapter<RecentCreationsAdapter.RecentCreationsViewHolder>() {
    class RecentCreationsViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecentCreationsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recent_creations_layout, parent, false) as ConstraintLayout)

    override fun onBindViewHolder(holder: RecentCreationsViewHolder, position: Int) = data.forEach { _ ->
        (holder.constraintLayout.getChildAt(0) as ImageView).setImageBitmap(data[position].bitmap)
        holder.constraintLayout.findViewById<TextView>(R.id.mtext).text = data[position].title
    }

    override fun getItemCount() = data.size
}