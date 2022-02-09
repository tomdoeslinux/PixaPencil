package com.realtomjoney.pyxlmoose.fragments.colorpicker.hex

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.databinding.FragmentHexadecimalColorPickerBinding
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.colorPaletteMode_
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_
import com.realtomjoney.pyxlmoose.utility.LongConstants

class HexadecimalColorPickerFragment : Fragment() {
    private val oldColorAsHex = Integer.toHexString(oldColor_)

    private fun setup() {
        binding.apply {
            fragmentHexadecimalColorPickerColorPreview.setBackgroundColor(oldColor_)
            fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.setText(oldColorAsHex)
        }
    }

    private fun setDoAfterTextChangedListeners() {
        binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.doAfterTextChanged {
            try {
                val text = binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.text.toString().replace("#", "")
                val color = Color.parseColor("#$text")
                binding.fragmentHexadecimalColorPickerColorPreview.setBackgroundColor(color)
            } catch (ex: Exception) { }
        }
    }

    private fun setOnClickListeners() {
        binding.fragmentRGBColorPickerDoneButton.setOnClickListener {
            hideKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    caller.onDoneButtonPressed((binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
                } catch (exception: Exception) {

                } finally {
                    showMenuItems()
                }
            }, LongConstants.DEF_HANDLER_DELAY)
        }
    }

    companion object {
        fun newInstance() = HexadecimalColorPickerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentHexadecimalColorPickerBinding.inflate(inflater, container, false)

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