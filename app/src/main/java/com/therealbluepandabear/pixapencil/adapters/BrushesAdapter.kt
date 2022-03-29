package com.therealbluepandabear.pixapencil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.BrushesListener
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.viewholders.BrushesViewHolder

class BrushesAdapter(private val data: List<Brush>, private val caller: BrushesListener) : RecyclerView.Adapter<BrushesViewHolder>()  {
    private lateinit var binding: BrushesLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrushesViewHolder {
        binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return BrushesViewHolder(binding.brushesLayoutRootLayout)
    }

    override fun onBindViewHolder(holder: BrushesViewHolder, position: Int) {
        binding.brushesLayoutMaterialCardView.apply parent@{
            val item = data[position]

            binding.brushesLayoutImageView.setImageResource(item.brushImage)

            binding.brushesLayoutImageView.setOnClickListener {
                caller.onBrushTapped(item)
            }

            this@parent.setOnClickListener {
                caller.onBrushTapped(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}