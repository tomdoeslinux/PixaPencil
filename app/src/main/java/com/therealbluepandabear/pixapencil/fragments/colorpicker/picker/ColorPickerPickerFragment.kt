package com.therealbluepandabear.pixapencil.fragments.colorpicker.picker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerPickerBinding
import com.therealbluepandabear.pixapencil.fragments.colorpicker.oldColor_

/**
 * Suppressed because the code is working fine how it is.
 */

@SuppressLint("ClickableViewAccessibility")

class ColorPickerPickerFragment : Fragment() {
    private var backingBindingProperty: FragmentColorPickerPickerBinding? = null

    val binding get(): FragmentColorPickerPickerBinding {
        return backingBindingProperty!!
    }

    var selectedColor: Int = 0

    companion object {
        fun newInstance(): ColorPickerPickerFragment {
            return ColorPickerPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentColorPickerPickerBinding.inflate(inflater, container, false)

        binding.fragmentColorPickerPickerColorPickerView.setInitialColor(oldColor_)
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(oldColor_)
        selectedColor = oldColor_
        setOnClickListeners()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }
}