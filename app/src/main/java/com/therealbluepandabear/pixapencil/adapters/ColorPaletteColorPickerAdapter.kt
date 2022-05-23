package com.therealbluepandabear.pixapencil.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPaletteColorPickerListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ColorPaletteColorPickerViewHolder

class ColorPaletteColorPickerAdapter(
    private val colorPalette: ColorPalette,
    private val caller: ColorPaletteColorPickerListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var colorData = mutableListOf<Int>()

    init {
        colorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ColorPickerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ColorPaletteColorPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ColorPaletteColorPickerViewHolder) {
            val isPlusIndicatorItemPosition = colorData[position] == Color.TRANSPARENT && position == colorData.size - 1

            holder.bind(colorData, position, isPlusIndicatorItemPosition)

            holder.binding.colorView.setOnClickListener {
                if (isPlusIndicatorItemPosition) {
                    caller.onColorAdded(colorPalette)
                } else {
                    caller.onColorTapped(colorData[position], it)
                }
            }

            holder.binding.colorView.setOnLongClickListener {
                if (!isPlusIndicatorItemPosition) {
                    caller.onColorLongTapped(colorPalette, position)
                }
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return colorData.size
    }

    fun updateDataSource(list: List<Int>){
        colorData.clear()
        colorData.addAll(list)
        notifyDataSetChanged()
    }
}
