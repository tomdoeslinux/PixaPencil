package com.therealbluepandabear.pixapencil.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.models.Brush

class BrushesViewHolder(val binding: BrushesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(brush: Brush) {
        binding.brushesLayoutImageView.setImageResource(brush.brushImage)
    }
}