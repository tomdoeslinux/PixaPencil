package com.realtomjoney.pyxlmoose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup, isGridVisible: Boolean)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.pixel_layout, parent, false)) {
    val tileParent: SquareFrameLayout = itemView.findViewById(R.id.pixelParent)

    init {
        val gridLineFrame: SquareFrameLayout = itemView.findViewById(R.id.gridLineFrame)

        if (!isGridVisible) gridLineFrame.visibility = View.INVISIBLE
    }
}