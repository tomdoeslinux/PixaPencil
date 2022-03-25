package com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hsv

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentHSVColorPickerBinding
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.oldColor_


class HSVColorPickerFragment : Fragment() {
    private val floatArray = FloatArray(3)

    private fun setup() {
        binding.fragmentHSVColorPickerColorPreview.setBackgroundColor(oldColor_)

        val hsv = FloatArray(3)

        Color.colorToHSV(oldColor_, hsv)

        hsvHue = hsv[0]
        hsvSaturation = hsv[1]
        hsvValue = hsv[2]

        binding.apply {
            fragmentHSVColorPickerHueValueTextInputEditText.setText(hsvHue.toInt().toString())
            fragmentHSVColorPickerSaturationValueTextInputEditText.setText(hsvSaturation.toInt().toString())
            fragmentHSVColorPickerValueValueTextInputEditText.setText(hsvValue.toInt().toString())
        }
    }

    private fun updateColorPickerColorView() {
        floatArray[0] = hsvHue
        floatArray[1] = hsvSaturation
        floatArray[2] = hsvValue

        binding.fragmentHSVColorPickerColorPreview.setBackgroundColor(Color.HSVToColor(
            floatArray
        ))
    }

    private fun setDoAfterTextChangedListeners() {
        binding.fragmentHSVColorPickerHueValueTextInputEditText.doAfterTextChanged {
            try {
                hsvHue = Integer.parseInt(binding.fragmentHSVColorPickerHueValueTextInputEditText.text.toString()).toFloat()
                updateColorPickerColorView()
            } catch (ex: Exception) { }
        }

        binding.fragmentHSVColorPickerSaturationValueTextInputEditText.doAfterTextChanged {
            try {
                hsvSaturation = Integer.parseInt(binding.fragmentHSVColorPickerSaturationValueTextInputEditText.text.toString()).toFloat()
                updateColorPickerColorView()
            } catch (ex: Exception) { }
        }

        binding.fragmentHSVColorPickerValueValueTextInputEditText.doAfterTextChanged {
            try {
                hsvValue = Integer.parseInt(binding.fragmentHSVColorPickerValueValueTextInputEditText.text.toString()).toFloat()
                updateColorPickerColorView()
            } catch (ex: Exception) { }
        }
    }

    companion object {
        fun newInstance(): HSVColorPickerFragment {
            return HSVColorPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentHSVColorPickerBinding.inflate(inflater, container, false)

        setOnClickListeners()
        setup()
        setDoAfterTextChangedListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}