package com.realtomjoney.pyxlmoose.fragments

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

class ColorPickerFragment(private val oldColor: Int) : Fragment() {

    private var _binding: FragmentColorPickerBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: ColorPickerFragmentListener

    private var valueR = 0
    private var valueG = 0
    private var valueB = 0

    companion object {
        fun newInstance(oldColor: Int) = ColorPickerFragment(oldColor)
    }

    private fun updateColorSelectedPreview() =  binding.colorPickerPreview.setBackgroundColor(Color.argb(255, valueR, valueG, valueB))

    private fun updateHexadecimalEditText() = binding.hexadecimalEditText.setText(Integer.toHexString((binding.colorPickerPreview.background as ColorDrawable).color))

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentColorPickerBinding.inflate(inflater, container, false)

        binding.oldColorPreview.setBackgroundColor(oldColor)
        binding.colorPickerPreview.setBackgroundColor(Color.argb(255, valueR, valueG, valueB))

        updateHexadecimalEditText()

        binding.hexadecimalEditText.doAfterTextChanged {
            try {
                val color = Color.parseColor("#" + binding.hexadecimalEditText.text.toString())
                binding.colorPickerPreview.setBackgroundColor(color)

                valueR = color.red
                valueG = color.green
                valueB = color.blue

            } catch (ex: Exception) { }
        }

        binding.colorDoneButton.setOnClickListener {
            caller.onDoneButtonPressed(Color.argb(255, valueR, valueG, valueB))
        }

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}