package com.realtomjoney.pyxlmoose

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColourPickerAdapter(private val list: List<Int>, private val caller: ColourPickerListener) : RecyclerView.Adapter<ColourPickerAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.colour_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.backgroundTintList = ColorStateList.valueOf(list[position])

        holder.view.setOnClickListener {
            caller.onColourTapped(list[position], it)
        }
    }

    override fun getItemCount() = list.size
}