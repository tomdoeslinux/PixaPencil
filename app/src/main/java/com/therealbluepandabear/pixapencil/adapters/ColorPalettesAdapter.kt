package com.therealbluepandabear.pixapencil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.databinding.ColorPalettesLayoutBinding
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ColorPalettesViewHolder

class ColorPalettesAdapter(
    private val data: MutableList<ColorPalette>,
    private val caller: ColorPalettesListener,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ColorPalettesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ColorPalettesViewHolder(binding, context)
    }

    private var previousViewElement: View? = null

    private var defSelected = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is ColorPalettesViewHolder) {
            holder.bind(item)

                if (position == selectedColorPaletteIndex) {
                    holder.binding.colorPalettesLayoutMaterialCardView.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            context,
                            R.color.recycler_view_item_tapped_color_daynight
                        )
                    previousViewElement = holder.binding.colorPalettesLayoutMaterialCardView
                    defSelected = true
                }


            holder.binding.colorPalettesLayoutMaterialCardView.setOnClickListener {
                caller.onColorPaletteTapped(item)

                previousViewElement?.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color_daynight)

                it.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_tapped_color_daynight)
                previousViewElement = it
            }

            holder.binding.colorPalettesLayoutMaterialCardView.setOnLongPressListener {
                caller.onColorPaletteLongTapped(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateDataSource(list: List<ColorPalette>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}