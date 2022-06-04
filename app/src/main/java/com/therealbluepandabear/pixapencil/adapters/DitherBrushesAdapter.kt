package com.therealbluepandabear.pixapencil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.DitherBrushesListener
import com.therealbluepandabear.pixapencil.models.DitherBrush
import com.therealbluepandabear.pixapencil.viewholders.DitherBrushesViewHolder

class DitherBrushesAdapter(
    private val data: List<DitherBrush>,
    private val caller: DitherBrushesListener,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return DitherBrushesViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is DitherBrushesViewHolder) {
            holder.setIsRecyclable(false)
            holder.bind(item)

            holder.binding.brushesLayoutMaterialCardView.setOnClickListener {
                caller.onDitherBrushTapped(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}