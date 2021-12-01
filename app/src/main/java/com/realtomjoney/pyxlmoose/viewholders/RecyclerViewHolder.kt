package com.realtomjoney.pyxlmoose.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.customviews.SquareFrameLayout

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup, isGridVisible: Boolean) : RecyclerView.ViewHolder(inflater.inflate(R.layout.pixel_layout, parent, false)) {
    val tileParent: SquareFrameLayout = itemView.findViewById(R.id.pixelParent)

    init {
        if (!isGridVisible) (itemView.findViewById<SquareFrameLayout>(R.id.gridLineFrame)).visibility = View.INVISIBLE
    }
}