package com.therealbluepandabear.pixapencil.adapters

import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPalettesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ViewHolder

class ColorPalettesAdapter(
    private val data: List<ColorPalette>,
    private val caller: ColorPalettesListener
) : RecyclerView.Adapter<ViewHolder<FrameLayout>>()  {

    private lateinit var binding: ColorPalettesLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<FrameLayout> {
        binding = ColorPalettesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.colorPalettesLayoutRootLayout)
    }

    private var previousViewElement: View? = null

    private var defSelected = false

    override fun onBindViewHolder(holder: ViewHolder<FrameLayout>, position: Int) = data.forEach { _ ->
        binding.colorPalettesLayoutMaterialCardView.apply parent@{
            val item = data[position]

            if (JsonConverter.convertJsonStringToListOfInt(item.colorPaletteColorData).size >= 2) {
                binding.colorPalettesLayoutFirstColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(item.colorPaletteColorData)[0])
                binding.colorPalettesLayoutSecondColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(item.colorPaletteColorData)[1])
            } else {
                binding.colorPalettesLayoutFirstColor.setBackgroundColor(Color.TRANSPARENT)
                binding.colorPalettesLayoutSecondColor.setBackgroundColor(Color.TRANSPARENT)
            }

            binding.apply {
                colorPalettesLayoutColorPaletteTitle?.text = item.colorPaletteName

                val layoutManager = LinearLayoutManager(context)

                val layoutManagerOrientation: Int = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    LinearLayoutManager.HORIZONTAL
                } else {
                    LinearLayoutManager.VERTICAL
                }

                layoutManager.orientation = layoutManagerOrientation
            }

            if (!defSelected) {
                this@parent.backgroundTintList = AppCompatResources.getColorStateList(context!!, R.color.colorPalettesBGSelected)
                previousViewElement = this
                defSelected = true
            }

            this@parent.setOnClickListener {
                caller.onColorPaletteTapped(item)

                previousViewElement?.backgroundTintList = AppCompatResources.getColorStateList(context!!, R.color.colorPaletteBG)

                it.backgroundTintList = AppCompatResources.getColorStateList(context!!, R.color.colorPalettesBGSelected)
                previousViewElement = it
            }

            this@parent.setOnLongClickListener {
                caller.onColorPaletteLongTapped(item)
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }
}