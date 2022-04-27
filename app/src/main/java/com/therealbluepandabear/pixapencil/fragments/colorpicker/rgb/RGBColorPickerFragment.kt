package com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentRGBColorPickerBinding
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.fragments.colorpicker.oldColor_
import com.therealbluepandabear.pixapencil.utility.InputFilterMinMax
import com.therealbluepandabear.pixapencil.utility.IntConstants
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.registerEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar


class RGBColorPickerFragment : Fragment() {
    private lateinit var keyboardVisibilityEventListenerRegistrar: Unregistrar

    fun unregisterKeyboardVisibilityEventListenerRegistrar() {
        if (::keyboardVisibilityEventListenerRegistrar.isInitialized) {
            keyboardVisibilityEventListenerRegistrar.unregister()
        }
    }

    private fun setup() {
        binding.apply {
            fragmentRGBColorPickerColorPreview.setBackgroundColor(oldColor_)

            val red = Color.red(oldColor_).toFloat()
            val green = Color.green(oldColor_).toFloat()
            val blue = Color.blue(oldColor_).toFloat()

            fragmentRGBColorPickerRedProgressBar.value = red
            fragmentRGBColorPickerGreenProgressBar.value = green
            fragmentRGBColorPickerBlueProgressBar.value = blue

            fragmentRGBColorPickerValueR.setText(red.toInt().toString())
            fragmentRGBColorPickerValueG.setText(green.toInt().toString())
            fragmentRGBColorPickerValueB.setText(blue.toInt().toString())
        }

        binding.textInputLayout1.editText?.filters = arrayOf(InputFilterMinMax(IntConstants.RGBMin, IntConstants.RGBMax))
        binding.textInputLayout2.editText?.filters = arrayOf(InputFilterMinMax(IntConstants.RGBMin, IntConstants.RGBMax))
        binding.textInputLayout3.editText?.filters = arrayOf(InputFilterMinMax(IntConstants.RGBMin, IntConstants.RGBMax))
    }

    private fun updateColorPickerColorPreview() {
        binding.fragmentRGBColorPickerColorPreview.setBackgroundColor(Color.argb(255, valueR.toInt(), valueG.toInt(), valueB.toInt()))
    }

    private fun setOnChangeListeners() {
        binding.apply {
            fragmentRGBColorPickerRedProgressBar.addOnChangeListener { _, value, _ ->
                valueR = value
                updateColorPickerColorPreview()
                fragmentRGBColorPickerValueR.setText(valueR.toInt().toString())
            }

            fragmentRGBColorPickerGreenProgressBar.addOnChangeListener { _, value, _ ->
                valueG = value
                updateColorPickerColorPreview()
                fragmentRGBColorPickerValueG.setText(valueG.toInt().toString())
            }

            fragmentRGBColorPickerBlueProgressBar.addOnChangeListener { _, value, _ ->
                valueB = value
                updateColorPickerColorPreview()
                fragmentRGBColorPickerValueB.setText(valueB.toInt().toString())
            }
        }

        binding.apply {
            fragmentRGBColorPickerValueR.doAfterTextChanged {
                try {
                    valueR = Integer.parseInt(fragmentRGBColorPickerValueR.text.toString()).toFloat()
                    valueG = Integer.parseInt(fragmentRGBColorPickerValueG.text.toString()).toFloat()
                    valueB = Integer.parseInt(fragmentRGBColorPickerValueB.text.toString()).toFloat()

                    updateColorPickerColorPreview()
                } catch (exception: Exception) { }
            }

            fragmentRGBColorPickerValueR.setOnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    try {
                        fragmentRGBColorPickerRedProgressBar.value = valueR
                        fragmentRGBColorPickerGreenProgressBar.value = valueG
                        fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            }

            fragmentRGBColorPickerValueR.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        fragmentRGBColorPickerRedProgressBar.value = valueR
                        fragmentRGBColorPickerGreenProgressBar.value = valueG
                        fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }

                    this@RGBColorPickerFragment.hideKeyboard()
                    return@setOnEditorActionListener true
                }
                false
            }

            fragmentRGBColorPickerValueG.doAfterTextChanged {
                try {
                    valueR = Integer.parseInt(fragmentRGBColorPickerValueR.text.toString()).toFloat()
                    valueG = Integer.parseInt(fragmentRGBColorPickerValueG.text.toString()).toFloat()
                    valueB = Integer.parseInt(fragmentRGBColorPickerValueB.text.toString()).toFloat()

                    updateColorPickerColorPreview()
                } catch (exception: Exception) { }
            }

            fragmentRGBColorPickerValueG.setOnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    try {
                        fragmentRGBColorPickerRedProgressBar.value = valueR
                        fragmentRGBColorPickerGreenProgressBar.value = valueG
                        fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            }

            fragmentRGBColorPickerValueG.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        fragmentRGBColorPickerRedProgressBar.value = valueR
                        fragmentRGBColorPickerGreenProgressBar.value = valueG
                        fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }

                    this@RGBColorPickerFragment.hideKeyboard()
                    return@setOnEditorActionListener true
                }
                false
            }

            fragmentRGBColorPickerValueB.doAfterTextChanged {
                try {
                    valueR = Integer.parseInt(fragmentRGBColorPickerValueR.text.toString()).toFloat()
                    valueG = Integer.parseInt(fragmentRGBColorPickerValueG.text.toString()).toFloat()
                    valueB = Integer.parseInt(fragmentRGBColorPickerValueB.text.toString()).toFloat()

                    updateColorPickerColorPreview()
                } catch (exception: Exception) { }
            }

            fragmentRGBColorPickerValueB.setOnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    try {
                        fragmentRGBColorPickerRedProgressBar.value = valueR
                        fragmentRGBColorPickerGreenProgressBar.value = valueG
                        fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            }

            fragmentRGBColorPickerValueB.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        fragmentRGBColorPickerRedProgressBar.value = valueR
                        fragmentRGBColorPickerGreenProgressBar.value = valueG
                        fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }

                    this@RGBColorPickerFragment.hideKeyboard()
                    return@setOnEditorActionListener true
                }
                false
            }
        }

        keyboardVisibilityEventListenerRegistrar = registerEventListener(requireActivity(),
            object : KeyboardVisibilityEventListener {
                override fun onVisibilityChanged(isOpen: Boolean) {
                    if (!isOpen) {
                        try {
                            binding.apply {
                                fragmentRGBColorPickerRedProgressBar.value = valueR
                                fragmentRGBColorPickerGreenProgressBar.value = valueG
                                fragmentRGBColorPickerBlueProgressBar.value = valueB
                            }
                        } catch (exception: Exception) { }
                    }
                }
            })
    }

    companion object {
        fun newInstance(): RGBColorPickerFragment {
            return RGBColorPickerFragment()
        }
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