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

class ColorPickerAdapter(private val caller: ColorPickerListener?) : RecyclerView.Adapter<ViewHolder<FrameLayout>>() {
    private lateinit var binding: ColorPickerLayoutBinding

    private lateinit var colorPalette: ColorPalette
    private lateinit var data: List<Int>

    private var cstr1Filled = false
    private var cstr2Filled = false

    constructor(colorPalette: ColorPalette, caller: ColorPickerListener?) : this(caller) {
        this.colorPalette = colorPalette
        colorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)
        this.cstr1Filled = true
    }

    constructor(data: List<Int>, caller: ColorPickerListener?) : this(caller) {
        this.data = data
        this.cstr2Filled = true
    }


    private var colorData = listOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<FrameLayout> {
        binding = ColorPickerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.colorPickerLayoutRootLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder<FrameLayout>, position: Int) {
        if (cstr1Filled) {
            val isPlusIndicatorItemPosition =
                colorData[position] == Color.TRANSPARENT && position == colorData.size - 1

            binding.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])

            if (isPlusIndicatorItemPosition) {
                binding.colorView.setBackgroundResource(R.drawable.ic_baseline_add_24)
                binding.colorView.background.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.GRAY,
                        BlendModeCompat.DST_OVER
                    )
            }

            binding.colorView.setOnClickListener {
                if (isPlusIndicatorItemPosition) {
                    caller?.onColorAdded(colorPalette)
                } else {
                    caller?.onColorTapped(colorData[position], it)
                }
            }
        } else {
            binding.colorView.setBackgroundColor(data[position])

            binding.colorView.setOnClickListener {
                caller?.onColorTapped(data[position], binding.colorView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return if (cstr1Filled) {
            colorData.size
        } else {
            data.size
        }
    }
}