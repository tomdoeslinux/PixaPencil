package com.realtomjoney.pyxlmoose.fragments.newcolorpalette

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentNewColorPaletteBinding
import com.realtomjoney.pyxlmoose.listeners.NewColorPaletteFragmentListener

class NewColorPaletteFragment : Fragment() {
    private fun setOnClickListeners() {
        binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
            caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString())
        }
    }

    companion object {
        fun newInstance() = NewColorPaletteFragment()
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