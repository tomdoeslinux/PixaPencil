package com.realtomjoney.pyxlmoose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.pixel_layout, parent, false)) {
    val tileParent: SquareFrameLayout = itemView.findViewById(R.id.pixelParent)
}