package com.therealbluepandabear.pixapencil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.viewholders.ColorPickerViewHolder

class ColorPickerAdapter(
    private val colorData: List<Int>,
    private val caller: ColorPickerListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ColorPickerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ColorPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ColorPickerViewHolder) {
            holder.bind(colorData, position)

            holder.binding.colorView.setOnClickListener {
                caller.onColorTapped(colorData[position], it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return colorData.size
    }
}