package com.realtomjoney.pyxlmoose

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColourPickerAdapter(private val list: List<Int>, private val caller: ColourPickerListener) : RecyclerView.Adapter<ColourPickerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColourPickerViewHolder {
        return ColourPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.colour_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColourPickerViewHolder, position: Int) {
        holder.colourView.setBackgroundResource(R.drawable.round)
        holder.colourView.backgroundTintList = ColorStateList.valueOf(list[position])

        holder.colourView.setOnClickListener {
            caller.onColourTapped(list[position], it)
        }

        if (position == 0) {
            caller.onColourTapped(list[position], holder.colourView)
        }
    }

    override fun getItemCount() = list.size
}