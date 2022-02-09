package com.realtomjoney.pyxlmoose.fragments.colorpicker.hsv

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.databinding.FragmentHSVColorPickerBinding
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.fragments.colorpicker.colorPaletteMode_
import com.realtomjoney.pyxlmoose.utility.LongConstants
import java.util.*


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

    private fun setOnClickListeners() {
        binding.fragmentHSVColorPickerDoneButton.setOnClickListener {
            hideKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    caller.onDoneButtonPressed((binding.fragmentHSVColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
                } catch (exception: Exception) {

                } finally {
                    showMenuItems()
                }
            }, LongConstants.DEF_HANDLER_DELAY)
        }
    }

    companion object {
        fun newInstance() = HSVColorPickerFragment()
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