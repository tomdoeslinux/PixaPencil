package com.therealbluepandabear.pixapencil.viewholders

import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding

class ColorPickerViewHolder(val binding: ColorPickerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorData: List<Int>, position: Int) {
        binding.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])
    }
}