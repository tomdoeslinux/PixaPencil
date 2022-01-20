package com.realtomjoney.pyxlmoose.fragments.colorpicker

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.widget.doAfterTextChanged
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerBinding

class ColorPickerFragment(private val oldColor: Int, val colorPaletteMode: Boolean = false) : Fragment() {
    private fun updateColorSelectedPreview() =  binding.colorPickerPreview.setBackgroundColor(Color.argb(255, valueR, valueG, valueB))
    private fun updateHexadecimalEditText() = binding.hexadecimalEditText.setText(Integer.toHexString((binding.colorPickerPreview.background as ColorDrawable).color))

    private fun setOnChangeListeners() {
        binding.redProgressBar.addOnChangeListener { _, value, _ ->
            valueR = value.toInt()
            updateColorSelectedPreview()
            updateHexadecimalEditText()
        }

        binding.greenProgressBar.addOnChangeListener { _, value, _ ->
            valueG = value.toInt()
            updateColorSelectedPreview()
            updateHexadecimalEditText()
        }

        binding.blueProgressBar.addOnChangeListener { _, value, _ ->
            valueB = value.toInt()
            updateColorSelectedPreview()
            updateHexadecimalEditText()
        }
    }

    private fun setDoAfterTextChangedListeners() {
        binding.hexadecimalEditText.doAfterTextChanged {
            try {
                val color = Color.parseColor("#" + binding.hexadecimalEditText.text.toString())
                binding.colorPickerPreview.setBackgroundColor(color)

                valueR = color.red
                valueG = color.green
                valueB = color.blue
            } catch (ex: Exception) { }
        }

    }

    companion object {
        fun newInstance(oldColor: Int, colorPaletteMode: Boolean = false) = ColorPickerFragment(oldColor, colorPaletteMode)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerBinding.inflate(inflater, container, false)

        binding.colorPickerPreview.setBackgroundColor(Color.argb(255, valueR, valueG, valueB))

        updateHexadecimalEditText()
        setDoAfterTextChangedListeners()
        setOnClickListeners()
        setOnChangeListeners()

        binding.redProgressBar.value = Color.red(oldColor).toFloat()
        binding.blueProgressBar.value = Color.blue(oldColor).toFloat()
        binding.greenProgressBar.value = Color.green(oldColor).toFloat()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}