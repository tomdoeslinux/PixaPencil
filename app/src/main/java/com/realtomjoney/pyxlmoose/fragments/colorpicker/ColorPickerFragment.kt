package com.realtomjoney.pyxlmoose.fragments.colorpicker

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.widget.doAfterTextChanged
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerBinding
import com.realtomjoney.pyxlmoose.listeners.RGBColorPickerFragmentListener

class ColorPickerFragment(private var oldColor: Int, private val colorPaletteMode: Boolean = false) : Fragment() {
    companion object {
        fun newInstance(oldColor: Int, colorPaletteMode: Boolean = false) = ColorPickerFragment(oldColor, colorPaletteMode)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerBinding.inflate(inflater, container, false)

        oldColor_ = oldColor
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}