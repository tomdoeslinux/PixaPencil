package com.realtomjoney.pyxlmoose

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColourPickerAdapter(private val list: List<Int>, private val caller: ColourPickerListener) : RecyclerView.Adapter<ColourPickerAdapter.ColourPickerViewHolder>() {
    class ColourPickerViewHolder(val colourView: View) : RecyclerView.ViewHolder(colourView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColourPickerViewHolder {
        return ColourPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.colour_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColourPickerViewHolder, position: Int) {
        holder.colourView.backgroundTintList = ColorStateList.valueOf(list[position])

        holder.colourView.setOnClickListener {
            caller.onColourTapped(list[position], it)
        }
    }

    override fun getItemCount() = list.size
}