package com.therealbluepandabear.pixapencil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.BrushesListener
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.viewholders.BrushesViewHolder
import com.therealbluepandabear.pixapencil.viewholders.ViewHolder

class BrushesAdapter(
    private val data: List<Brush>,
    private val caller: BrushesListener,
    private val context: Context
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return BrushesViewHolder(binding)
    }

    private var previousViewElement: View? = null

    private var defSelected = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is BrushesViewHolder) {
            holder.bind(item)

            if (!defSelected) {
                holder.binding.brushesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.colorPalettesBGSelected)
                previousViewElement = holder.binding.brushesLayoutMaterialCardView
                defSelected = true
            }

            holder.binding.brushesLayoutMaterialCardView.setOnClickListener {
                caller.onBrushTapped(item)

                previousViewElement?.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.white)

                it.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.colorPalettesBGSelected)
                previousViewElement = it
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}