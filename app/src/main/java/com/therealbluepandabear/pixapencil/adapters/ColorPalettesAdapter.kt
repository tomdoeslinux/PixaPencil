package com.therealbluepandabear.pixapencil.adapters

import android.app.Activity
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.ColorPalettesLayoutBinding
import com.therealbluepandabear.pixapencil.extensions.getScreenOrientation
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ColorPalettesViewHolder

class ColorPalettesAdapter(
    private val callerActivity: Activity,
    private val data: List<ColorPalette>,
    private val caller: ColorPalettesListener
    ) : RecyclerView.Adapter<ColorPalettesViewHolder>()  {

    private lateinit var binding: ColorPalettesLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPalettesViewHolder {
        binding = ColorPalettesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ColorPalettesViewHolder(binding.colorPalettesLayoutRootLayout)
    }

    override fun onBindViewHolder(holder: ColorPalettesViewHolder, position: Int) = data.forEach { _ ->
        binding.colorPalettesLayoutMaterialCardView.apply parent@{
            val item = data[position]

            binding.apply {
                colorPalettesLayoutColorPaletteTitle?.text = item.colorPaletteName

                val layoutManager = LinearLayoutManager(context)

                val layoutManagerOrientation: Int = if (callerActivity.getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
                    LinearLayoutManager.HORIZONTAL
                } else {
                    LinearLayoutManager.VERTICAL
                }

                layoutManager.orientation = layoutManagerOrientation

                colorPalettesLayoutColorPalettePreviewRecyclerView.layoutManager = layoutManager
                colorPalettesLayoutColorPalettePreviewRecyclerView.adapter = ColorPickerAdapter(item, null, isPreviewMode = true)
            }

            binding.colorPalettesLayoutClickDetector.setOnClickListener {
                caller.onColorPaletteTapped(item)
            }

            this@parent.setOnClickListener {
                caller.onColorPaletteTapped(item)
            }

            this@parent.setOnLongClickListener {
                caller.onColorPaletteLongTapped(item)
                true
            }

            binding.colorPalettesLayoutClickDetector.setOnLongClickListener {
                caller.onColorPaletteLongTapped(item)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}