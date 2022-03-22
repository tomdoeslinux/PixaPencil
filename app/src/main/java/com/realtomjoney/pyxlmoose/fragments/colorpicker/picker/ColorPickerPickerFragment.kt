package com.realtomjoney.pyxlmoose.fragments.colorpicker.picker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.customviews.colorpickerview.ColorPickerView
import com.realtomjoney.pyxlmoose.customviews.colorpickerview.colorPickerColorSelected
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerPickerBinding
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_


/**
 * Suppressed because the code is working fine how it is.
 */

@SuppressLint("ClickableViewAccessibility")

class ColorPickerPickerFragment : Fragment(), View.OnTouchListener {
    private fun updatePreview() {
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(colorPickerColorSelected)
    }

    private fun configureColorPickerView() {
        val colorPickerViewInstance = ColorPickerView(requireContext())
        binding.defContainer.addView(colorPickerViewInstance)
        colorPickerViewInstance.setOnTouchListener(this)
    }


    private fun setup() {
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(oldColor_)
        configureColorPickerView()
        setOnClickListeners()
    }

    companion object {
        fun newInstance(): ColorPickerPickerFragment {
            return ColorPickerPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerPickerBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        val coordinateX = event.x.toInt()
        val coordinateY = event.y.toInt()

        val bitmap = v!!.drawToBitmap()

        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height

        return when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                if (coordinateX in 0 until bitmapWidth && coordinateY in 0 until bitmap.height) {
                    colorPickerColorSelected = bitmap.getPixel(coordinateX, coordinateY)
                    updatePreview()
                    true
                } else {
                    false
                }
            }
            MotionEvent.ACTION_DOWN -> {
                if (coordinateX in 0 until bitmapWidth && coordinateY in 0 until bitmapHeight) {
                    colorPickerColorSelected = bitmap.getPixel(coordinateX, coordinateY)
                    updatePreview()
                    true
                } else {
                    false
                }
            }
            else -> {
                false
            }
        }
    }
}