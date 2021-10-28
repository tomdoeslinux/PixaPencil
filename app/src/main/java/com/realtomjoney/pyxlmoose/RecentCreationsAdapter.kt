package com.realtomjoney.pyxlmoose

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.text.TextUtils

class RecentCreationsAdapter(private val data: List<SavedPixelArt>, private val listener: RecentCreationsListener) : RecyclerView.Adapter<RecentCreationsAdapter.RecentCreationsViewHolder>() {
    class RecentCreationsViewHolder(val frame: FrameLayout) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecentCreationsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recent_creations_layout, parent, false) as FrameLayout)

    override fun onBindViewHolder(holder: RecentCreationsViewHolder, position: Int) = data.forEach { _ ->
        val rootLayout = holder.frame.findViewById<FrameLayout>(R.id.rootFrameLayout)
        with (rootLayout.findViewById<CardView>(R.id.mCard))  {
            findViewById<ImageView>(R.id.mImageView).setImageBitmap(data[position].bitmap)
            val title: TextView = findViewById(R.id.mtext)

            if (data[position].title.length > 6) {
                title.ellipsize = TextUtils.TruncateAt.MARQUEE;
                title.isSelected = true
                title.isSingleLine = true
                title.text = (data[position].title + "                    ").repeat(200)
            } else {
                title.text = data[position].title
            }

            this.setOnClickListener {
                listener.onCreationTapped(data[position])
            }
        }

    }

    override fun getItemCount() = data.size
}