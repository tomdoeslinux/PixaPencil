package com.realtomjoney.pyxlmoose.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.listeners.ColorPickerListener
import com.realtomjoney.pyxlmoose.viewholders.ColorPickerViewHolder

class ColorPickerAdapter(private val list: List<Int>, private val caller: ColorPickerListener) : RecyclerView.Adapter<ColorPickerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        return ColorPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.color_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.colorView.setBackgroundResource(R.drawable.round)
        holder.colorView.backgroundTintList = ColorStateList.valueOf(list[position])

        holder.colorView.setOnClickListener {
            caller.onColorTapped(list[position], it)
        }

        if (position == 0) {
            caller.onColorTapped(list[position], holder.colorView)
        }
    }

    override fun getItemCount() = list.size
}