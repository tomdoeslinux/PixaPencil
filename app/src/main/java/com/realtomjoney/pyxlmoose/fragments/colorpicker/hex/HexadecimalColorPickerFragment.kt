package com.realtomjoney.pyxlmoose.fragments.colorpicker.hex

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
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.databinding.FragmentHexadecimalColorPickerBinding
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_
import java.util.*

class HexadecimalColorPickerFragment : Fragment() {
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
        binding.fragmentHexadecimalColorPickerColorPreview.setBackgroundColor(oldColor_)
        binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.setText(Integer.toHexString(oldColor_))
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
                    caller.onDoneButtonPressed((binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color)
                } catch (exception: Exception) {

                } finally {
                    showMenuItems()
                }
            }, 40)
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