package com.therealbluepandabear.pixapencil.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
import com.therealbluepandabear.pixapencil.listeners.ColorPaletteColorPickerListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ColorPaletteColorPickerViewHolder

class ColorPaletteColorPickerAdapter(
    private val colorPalette: ColorPalette,
    private val caller: ColorPaletteColorPickerListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ColorPickerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ColorPaletteColorPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ColorPaletteColorPickerViewHolder) {
            val colorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
            val isPlusIndicatorItemPosition = colorData[position] == Color.TRANSPARENT && position == colorData.size - 1

            holder.bind(colorData, position, isPlusIndicatorItemPosition)

            holder.binding.colorView.setOnClickListener {
                if (isPlusIndicatorItemPosition) {
                    caller.onColorAdded(colorPalette)
                } else {
                    caller.onColorTapped(colorData[position], it)
                }
            }

            holder.binding.colorView.setOnLongPressListener {
                if (!isPlusIndicatorItemPosition) {
                    caller.onColorLongTapped(colorPalette, position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        val colorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        return colorData.size
    }

    fun updateDataSource(list: List<Int>){
        val colorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        colorData.clear()
        colorData.addAll(list)
        notifyDataSetChanged()
    }
}
