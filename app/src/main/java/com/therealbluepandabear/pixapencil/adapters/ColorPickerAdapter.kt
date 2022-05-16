package com.therealbluepandabear.pixapencil.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ViewHolder

class ColorPickerAdapter(
    private val data: ColorPalette,
    private val caller: ColorPickerListener?,
    private val isPaletteMode: Boolean = true,
    private val isPreviewMode: Boolean = false) : RecyclerView.Adapter<ViewHolder<FrameLayout>>() {
    private lateinit var binding: ColorPickerLayoutBinding

    private var colorData = listOf<Int>()

    init {
        colorData = JsonConverter.convertJsonStringToListOfInt(data.colorPaletteColorData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<FrameLayout> {
        binding = ColorPickerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.colorPickerLayoutRootLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder<FrameLayout>, position: Int) {
        binding.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])

        if (!isPreviewMode) {
            binding.colorView.setOnLongClickListener {
                if (colorData[position] != Color.TRANSPARENT) {
                    caller!!.onColorLongTapped(data, position)
                }
                true
            }
        }

        if (isPaletteMode && !isPreviewMode) {
            if (colorData[position] == Color.TRANSPARENT && position == colorData.size - 1) {
                binding.colorView.setBackgroundResource(R.drawable.ic_baseline_add_24)
                binding.colorView.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.GRAY, BlendModeCompat.DST_OVER)
            }
        }

        binding.colorView.setOnClickListener {
            if (colorData[position] != Color.TRANSPARENT) {
                caller?.onColorTapped(colorData[position], it)
            } else if (colorData[position] == Color.TRANSPARENT){
                caller?.onColorAdded(data)
            }
        }

        if (position == 0) {
            caller?.onColorTapped(colorData[position], binding.colorView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return colorData.size
    }
}