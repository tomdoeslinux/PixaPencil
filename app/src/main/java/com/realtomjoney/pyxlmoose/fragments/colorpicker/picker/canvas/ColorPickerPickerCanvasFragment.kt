package com.realtomjoney.pyxlmoose.fragments.colorpicker.picker.canvas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.customviews.colorpickerview.ColorPickerView
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerPickerCanvasBinding

class ColorPickerPickerCanvasFragment : Fragment() {
    private lateinit var colorPickerViewInstance: ColorPickerView


    private fun setupCanvas() {
        colorPickerViewInstance = ColorPickerView(requireContext())
        binding.fragmentColorPickerPickerCanvasRootLayout.addView(colorPickerViewInstance)
    }

    companion object {
        fun newInstance() = ColorPickerPickerCanvasFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerPickerCanvasBinding.inflate(inflater, container, false)

        setupCanvas()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}