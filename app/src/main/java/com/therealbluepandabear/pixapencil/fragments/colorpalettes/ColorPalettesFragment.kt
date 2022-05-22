package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.adapters.ColorPalettesAdapter
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPalettesBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesFragmentListener
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesListener
import com.therealbluepandabear.pixapencil.models.ColorPalette

class ColorPalettesFragment : Fragment(), ColorPalettesListener {
    lateinit var adapter : ColorPalettesAdapter
    val colorPalettesList = mutableListOf<ColorPalette>()

    companion object {
        fun newInstance(): ColorPalettesFragment {
            return ColorPalettesFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPalettesFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPalettesBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        observeColorPaletteData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        caller.onColorPaletteTapped(selectedColorPalette)
    }

    override fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette) {
        caller.onColorPaletteLongTapped(selectedColorPalette)
    }
}