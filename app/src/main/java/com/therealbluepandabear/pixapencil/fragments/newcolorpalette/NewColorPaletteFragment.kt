package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentNewColorPaletteBinding
import com.therealbluepandabear.pixapencil.listeners.NewColorPaletteFragmentListener

class NewColorPaletteFragment : Fragment() {
    companion object {
        fun newInstance(): NewColorPaletteFragment {
            return NewColorPaletteFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewColorPaletteFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentNewColorPaletteBinding.inflate(inflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}