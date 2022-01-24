package com.realtomjoney.pyxlmoose.fragments.colorpicker.rgb

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.databinding.FragmentRGBColorPickerBinding
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.colorPaletteMode_
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_

class RGBColorPickerFragment : Fragment() {
    private fun setup() {
        binding.apply {
            fragmentRGBColorPickerColorPreview.setBackgroundColor(oldColor_)
            fragmentRGBColorPickerRedProgressBar.value = Color.red(oldColor_).toFloat()
            fragmentRGBColorPickerBlueProgressBar.value = Color.blue(oldColor_).toFloat()
            fragmentRGBColorPickerGreenProgressBar.value = Color.green(oldColor_).toFloat()
        }
    }

    private fun updateColorSelectedPreview() {
        binding.fragmentRGBColorPickerColorPreview.setBackgroundColor(Color.argb(255, valueR.toInt(), valueG.toInt(), valueB.toInt()))
    }

    private fun setOnChangeListeners() {
        binding.fragmentRGBColorPickerRedProgressBar.addOnChangeListener { _, value, _ ->
            valueR = value
            updateColorSelectedPreview()
        }

        binding.fragmentRGBColorPickerGreenProgressBar.addOnChangeListener { _, value, _ ->
            valueG = value
            updateColorSelectedPreview()
        }

        binding.fragmentRGBColorPickerBlueProgressBar.addOnChangeListener { _, value, _ ->
            valueB = value
            updateColorSelectedPreview()
        }
    }

    private fun setOnClickListeners() {
        binding.fragmentRGBColorPickerDoneButton.setOnClickListener {
            hideKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    caller.onDoneButtonPressed((binding.fragmentRGBColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
                } catch (exception: Exception) {

                } finally {
                    showMenuItems()
                }
            }, 40)
        }
    }

    companion object {
        fun newInstance() = RGBColorPickerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentRGBColorPickerBinding.inflate(inflater, container, false)

        setOnClickListeners()
        setup()
        setOnChangeListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}