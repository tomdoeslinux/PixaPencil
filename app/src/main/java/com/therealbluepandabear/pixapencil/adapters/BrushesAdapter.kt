package com.therealbluepandabear.pixapencil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.BrushesListener
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.viewholders.BrushesViewHolder

class BrushesAdapter(
    private val data: List<Brush>,
    private val caller: BrushesListener,
    private val context: Context
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return BrushesViewHolder(binding, context)
    }

    private var previousViewElement: View? = null

    private var defSelected = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is BrushesViewHolder) {
            holder.bind(item)

            if (!defSelected) {
                holder.binding.brushesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_tapped_color)
                previousViewElement = holder.binding.brushesLayoutMaterialCardView
                defSelected = true
            }

            holder.binding.brushesLayoutMaterialCardView.setOnClickListener {
                caller.onBrushTapped(item)

                previousViewElement?.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color)

                it.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_tapped_color)
                previousViewElement = it
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}