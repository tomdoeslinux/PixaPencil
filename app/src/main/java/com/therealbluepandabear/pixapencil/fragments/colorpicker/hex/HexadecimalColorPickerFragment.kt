package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

/**
 * Fragment Structure -> If you are making any changes to the code, follow these guidelines:
 *
 * ,------------,
 * [    Root    ]
 * '------------'
 *      │
 *      ▼
 * ,---------------,
 * [    Binding    ]
 * '---------------'
 *      │
 *      ▼
 * ,--------------,
 * [    Caller    ]
 * '--------------'
 *      │
 *      ▼
 * ,-------------,
 * [    Title    ]
 * '-------------'
 *      │
 *      ▼
 * ,-----------------,
 * [    Variables    ]
 * '-----------------'
 *      │
 *      ▼
 * ,-----------------------------------,
 * [    Private Functions/Functions    ]
 * '-----------------------------------'
 *      │
 *      ▼
 * ,------------------------,
 * [    Companion Object    ]
 * '------------------------'
 *      │
 *      ▼
 * ,----------------------------------,
 * [    Interface Caller Functions    ]
 * '----------------------------------'
 *      │
 *      ▼
 * ,---------------,
 * [    OnAttach   ]
 * '---------------'
 *      │
 *      ▼
 * ,--------------------------,
 * [    OnCreateOptionsMenu   ]
 * '--------------------------'
 *      │
 *      ▼
 * ,-------------------,
 * [    OnCreateView   ]
 * '-------------------'
 *      │
 *      ▼
 * ,---------------,
 * [    OnCreate   ]
 * '---------------'
 *      │
 *      ▼
 * ,--------------------,
 * [    OnDestroyView   ]
 * '--------------------'
 */

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentHexadecimalColorPickerBinding
import com.therealbluepandabear.pixapencil.fragments.colorpicker.oldColor_

class HexadecimalColorPickerFragment : Fragment() {
    private var backingBindingProperty: FragmentHexadecimalColorPickerBinding? = null

    val binding get(): FragmentHexadecimalColorPickerBinding {
        return backingBindingProperty!!
    }

    private val oldColorAsHex = Integer.toHexString(oldColor_)

    private fun setup() {
        binding.fragmentHexadecimalColorPickerColorPreview.setBackgroundColor(oldColor_)
        binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.setText(oldColorAsHex)
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
        backingBindingProperty = FragmentHexadecimalColorPickerBinding.inflate(inflater, container, false)

        setOnClickListeners()
        setup()
        setDoAfterTextChangedListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }
}