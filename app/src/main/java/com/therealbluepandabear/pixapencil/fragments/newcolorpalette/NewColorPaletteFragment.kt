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

package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentNewColorPaletteBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.NewColorPaletteFragmentListener

class NewColorPaletteFragment : Fragment(), ActivityFragment {
    override val title: String by lazy { getString(R.string.fragment_new_color_palette_title) }

    private var _binding: FragmentNewColorPaletteBinding? = null

    val binding get(): FragmentNewColorPaletteBinding {
        return _binding!!
    }

    lateinit var caller: NewColorPaletteFragmentListener

    companion object {
        fun newInstance(): NewColorPaletteFragment {
            return NewColorPaletteFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewColorPaletteFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewColorPaletteBinding.inflate(inflater, container, false)

        setOnClickListeners()

        binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.doAfterTextChanged {
            if (binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString().isNotBlank()) {
                binding.fragmentNewColorPaletteColorPaletteNameTextInputLayout.isErrorEnabled = false
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}