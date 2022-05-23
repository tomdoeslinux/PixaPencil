package com.therealbluepandabear.pixapencil.viewholders

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.models.Brush

class BrushesViewHolder(val binding: BrushesLayoutBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(brush: Brush) {
        binding.brushesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color)
        binding.brushesLayoutImageView.setImageResource(brush.brushImage)
    }
}