package com.therealbluepandabear.pyxlmoose.fragments.colorpicker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pyxlmoose.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pyxlmoose.listeners.ColorPickerFragmentListener

class ColorPickerFragment : Fragment() {
    private var paramOldColor: Int? = null
    private var paramColorPaletteMode: Boolean = false

    fun setParams(paramOldColor: Int, paramColorPaletteMode: Boolean) {
        this.paramOldColor = paramOldColor
        this.paramColorPaletteMode = paramColorPaletteMode
    }

    private fun instantiateVariables() {
        if (paramOldColor != null) {
            oldColor_ = paramOldColor!!
        }
        colorPaletteMode_ = paramColorPaletteMode
    }

    private fun setup() {
        instantiateVariables()
        setOnClickListeners()
    }

    companion object {
        fun newInstance(paramOldColor: Int, paramColorPaletteMode: Boolean = false): ColorPickerFragment {
            val fragment = ColorPickerFragment()
            fragment.setParams(paramOldColor, paramColorPaletteMode)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}