package com.realtomjoney.pyxlmoose.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.listeners.ColorPickerListener
import com.realtomjoney.pyxlmoose.viewholders.ColorPickerViewHolder

class ColourPickerAdapter(private val list: List<Int>, private val caller: ColorPickerListener) : RecyclerView.Adapter<ColorPickerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        return ColorPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.colour_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
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