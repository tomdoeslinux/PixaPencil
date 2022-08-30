package com.therealbluepandabear.pixapencil.viewholders

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPalettesLayoutBinding
import com.therealbluepandabear.pixapencil.models.ColorPalette

class ColorPalettesViewHolder(val binding: ColorPalettesLayoutBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorPalette: ColorPalette) {
        binding.colorPalettesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color_daynight)

        val colorPaletteColorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        colorPaletteColorData.removeLast()

        if (colorPaletteColorData.size >= 2) {
            if (binding.colorPalettesLayoutSecondColorRoot.visibility == View.INVISIBLE) {
                binding.colorPalettesLayoutSecondColorRoot.visibility = View.VISIBLE
            }

            binding.colorPalettesLayoutFirstColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[0])
            binding.colorPalettesLayoutSecondColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[1])
        } else if (colorPaletteColorData.size == 1) {
            if (binding.colorPalettesLayoutFirstColorRoot.visibility == View.INVISIBLE) {
                binding.colorPalettesLayoutFirstColorRoot.visibility = View.VISIBLE
            }

            binding.colorPalettesLayoutFirstColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[0])
            binding.colorPalettesLayoutSecondColorRoot.visibility = View.INVISIBLE
        } else {
            binding.colorPalettesLayoutFirstColorRoot.visibility = View.INVISIBLE
            binding.colorPalettesLayoutSecondColorRoot.visibility = View.INVISIBLE
        }

        binding.colorPalettesLayoutColorPaletteTitle?.text = colorPalette.colorPaletteName
    }
}