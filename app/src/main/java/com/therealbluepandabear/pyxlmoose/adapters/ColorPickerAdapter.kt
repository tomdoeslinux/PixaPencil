package com.therealbluepandabear.pyxlmoose.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.R
import com.therealbluepandabear.pyxlmoose.converters.JsonConverter
import com.therealbluepandabear.pyxlmoose.listeners.ColorPickerListener
import com.therealbluepandabear.pyxlmoose.models.ColorPalette
import com.therealbluepandabear.pyxlmoose.viewholders.ColorPickerViewHolder

class ColorPickerAdapter(private val data: ColorPalette, private val caller: ColorPickerListener?, private val isPaletteMode: Boolean = true, private val isPreviewMode: Boolean = false) : RecyclerView.Adapter<ColorPickerViewHolder>() {
    private var colorData = listOf<Int>()

    init {
        colorData = JsonConverter.convertJsonStringToListOfInt(data.colorPaletteColorData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        return ColorPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.color_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])

        holder.colorView.setOnLongClickListener {
            if (colorData[position] != Color.TRANSPARENT) {
                caller!!.onColorLongTapped(data, position)
            }
            true
        }

        if (isPaletteMode && !isPreviewMode) {
            if (colorData[position] == Color.TRANSPARENT) {
                holder.colorView.setBackgroundResource(R.drawable.ic_baseline_add_24)
                holder.colorView.background.setColorFilter(
                    Color.GRAY,
                    PorterDuff.Mode.DST_OVER
                )
            }
        }

        holder.colorView.setOnClickListener {
            if (colorData[position] != Color.TRANSPARENT) {
                caller?.onColorTapped(colorData[position], it)
            } else {
                caller?.onColorAdded(data)
            }
        }

        if (position == 0) {
            caller?.onColorTapped(colorData[position], holder.colorView)
        }
    }

    override fun getItemCount(): Int {
        return colorData.size
    }
}