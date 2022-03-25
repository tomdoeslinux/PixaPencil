package com.therealbluepandabear.pyxlmoose.fragments.colorpicker.hex

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pyxlmoose.databinding.FragmentHexadecimalColorPickerBinding
import com.therealbluepandabear.pyxlmoose.fragments.colorpicker.oldColor_

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

    companion object {
        fun newInstance(): HexadecimalColorPickerFragment {
            return HexadecimalColorPickerFragment()
        }
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