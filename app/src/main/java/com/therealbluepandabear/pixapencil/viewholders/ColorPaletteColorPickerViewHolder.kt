package com.therealbluepandabear.pixapencil.viewholders

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding

class ColorPaletteColorPickerViewHolder(val binding: ColorPickerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorData: List<Int>, position: Int, isPlusIndicatorItemPosition: Boolean) {
        binding.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])

        if (isPlusIndicatorItemPosition) {
            binding.colorView.setBackgroundResource(R.drawable.ic_baseline_add_24)
            binding.colorView.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.GRAY, BlendModeCompat.DST_OVER)
        }
    }
}