/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    private var _binding: FragmentColorPalettesBinding? = null

    val binding get(): FragmentColorPalettesBinding {
        return _binding!!
    }

    private lateinit var caller: ColorPalettesFragmentListener

    lateinit var adapter : ColorPalettesAdapter
    val colorPalettesList = mutableListOf<ColorPalette>()

    private fun setup() {
        setUpRecyclerView()
        observeColorPaletteData()
    }

    companion object {
        fun newInstance(): ColorPalettesFragment {
            return ColorPalettesFragment()
        }
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        caller.onColorPaletteTapped(selectedColorPalette)
    }

    override fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette) {
        caller.onColorPaletteLongTapped(selectedColorPalette)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPalettesFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentColorPalettesBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}