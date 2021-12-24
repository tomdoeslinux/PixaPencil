package com.realtomjoney.pyxlmoose.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.listeners.ColorPickerListener
import com.realtomjoney.pyxlmoose.models.ColorPalette
import com.realtomjoney.pyxlmoose.viewholders.ColorPickerViewHolder

class ColorPickerAdapter(private val data: ColorPalette, private val caller: ColorPickerListener?, private val isPaletteMode: Boolean = true) : RecyclerView.Adapter<ColorPickerViewHolder>() {
    private fun extractColorDataFromColorPalette(): List<Int> {
        val data = JsonConverter.convertJsonStringToListOfInt(data.colorPaletteColorData).toMutableList()

        if (!data.contains(Color.TRANSPARENT) && isPaletteMode) data.add(Color.TRANSPARENT)
        return data.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        return ColorPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.color_picker_layout, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.colorView.setBackgroundResource(R.drawable.round)
        holder.colorView.backgroundTintList = ColorStateList.valueOf(extractColorDataFromColorPalette()[position])

        if (extractColorDataFromColorPalette()[position] == Color.TRANSPARENT) {
            holder.colorView.setBackgroundResource(R.drawable.ic_baseline_add_24)
            holder.colorView.background.setColorFilter(
                Color.GRAY,
                PorterDuff.Mode.DST_OVER
            )
        }

        holder.colorView.setOnClickListener {
            if (extractColorDataFromColorPalette()[position] != Color.TRANSPARENT) {
                caller?.onColorTapped(extractColorDataFromColorPalette()[position], it)
            } else {
                caller?.onColorAdded(data)
            }
        }

        if (position == 0) {
            caller?.onColorTapped(extractColorDataFromColorPalette()[position], holder.colorView)
        }
    }

    override fun getItemCount() = extractColorDataFromColorPalette().size
}