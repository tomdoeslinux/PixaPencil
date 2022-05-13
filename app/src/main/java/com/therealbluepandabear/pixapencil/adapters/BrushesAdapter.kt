package com.therealbluepandabear.pixapencil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.BrushesListener
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.viewholders.ViewHolder

class BrushesAdapter(private val data: List<Brush>, private val caller: BrushesListener) : RecyclerView.Adapter<ViewHolder<FrameLayout>>()  {
    private lateinit var binding: BrushesLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<FrameLayout> {
        binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.brushesLayoutRootLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder<FrameLayout>, position: Int) {
        binding.brushesLayoutMaterialCardView.apply parent@{
            val item = data[position]

            binding.brushesLayoutImageView.setImageResource(item.brushImage)

            this@parent.setOnClickListener {
                caller.onBrushTapped(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}