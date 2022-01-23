package com.realtomjoney.pyxlmoose.fragments.colorpicker.rgb

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.databinding.FragmentRGBColorPickerBinding
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.colorPaletteMode_
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_
import java.util.*

class RGBColorPickerFragment : Fragment() {
    private fun hideKeyboard() {
        try {
            val inputMethodManager =
                (Objects.requireNonNull(requireActivity()).getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as InputMethodManager)
            inputMethodManager.hideSoftInputFromWindow(
                this.requireActivity().currentFocus!!.windowToken, 0
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun setup() {
        binding.fragmentRGBColorPickerColorPreview.setBackgroundColor(oldColor_)
        binding.fragmentRGBColorPickerRedProgressBar.value = Color.red(oldColor_).toFloat()
        binding.fragmentRGBColorPickerBlueProgressBar.value = Color.blue(oldColor_).toFloat()
        binding.fragmentRGBColorPickerGreenProgressBar.value = Color.green(oldColor_).toFloat()
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