package com.therealbluepandabear.pixapencil.viewholders

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPalettesLayoutBinding
import com.therealbluepandabear.pixapencil.models.ColorPalette

class ColorPalettesViewHolder(val binding: ColorPalettesLayoutBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorPalette: ColorPalette) {
        if (JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).size >= 2) {
            binding.colorPalettesLayoutFirstColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[0])
            binding.colorPalettesLayoutSecondColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[1])
        } else {
            binding.colorPalettesLayoutFirstColor.setBackgroundColor(Color.TRANSPARENT)
            binding.colorPalettesLayoutSecondColor.setBackgroundColor(Color.TRANSPARENT)
        }

        binding.colorPalettesLayoutColorPaletteTitle?.text = colorPalette.colorPaletteName
    }
}