package com.realtomjoney.pyxlmoose

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerBinding

class ColorPickerFragment : Fragment() {

    private var _binding: FragmentColorPickerBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: ColorPickerFragmentListener

    private var valueR = 0
    private var valueG = 0
    private var valueB = 0

    companion object {
        fun newInstance() = ColorPickerFragment()
    }

    private fun updateColorSelectedPreview() =  binding.colorPickerPreview.setBackgroundColor(Color.argb(255, valueR, valueG, valueB))

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentColorPickerBinding.inflate(inflater, container, false)

        binding.colorPickerPreview.setBackgroundColor(Color.argb(255, valueR, valueG, valueB))

        binding.colorDoneButton.setOnClickListener {
            caller.onDoneButtonPressed()
        }

        binding.redProgressBar.addOnChangeListener { _, value, _ ->
            valueR = value.toInt()
            updateColorSelectedPreview()
        }

        binding.greenProgressBar.addOnChangeListener { _, value, _ ->
            valueG = value.toInt()
            updateColorSelectedPreview()
        }

        binding.blueProgressBar.addOnChangeListener { _, value, _ ->
            valueB = value.toInt()
            updateColorSelectedPreview()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}