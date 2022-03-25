package com.therealbluepandabear.pyxlmoose.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pyxlmoose.listeners.BrushesListener
import com.therealbluepandabear.pyxlmoose.models.Brush
import com.therealbluepandabear.pyxlmoose.viewholders.BrushesViewHolder

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