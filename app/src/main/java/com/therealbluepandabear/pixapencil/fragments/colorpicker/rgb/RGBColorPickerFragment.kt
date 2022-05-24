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
import com.therealbluepandabear.pixapencil.fragments.colorpicker.oldColor_
import com.therealbluepandabear.pixapencil.utility.InputFilterMinMax
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.registerEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar


class RGBColorPickerFragment : Fragment() {
    private lateinit var keyboardVisibilityEventListenerRegistrar: Unregistrar

    private var backingBindingProperty: FragmentRGBColorPickerBinding? = null

    val binding get(): FragmentRGBColorPickerBinding {
        return backingBindingProperty!!
    }

    private var valueR = 0f
    private var valueG = 0f
    private var valueB = 0f

    fun unregisterKeyboardVisibilityEventListenerRegistrar() {
        if (::keyboardVisibilityEventListenerRegistrar.isInitialized) {
            keyboardVisibilityEventListenerRegistrar.unregister()
        }
    }

    private fun setup() {
        binding.fragmentRGBColorPickerColorPreview.setBackgroundColor(oldColor_)

        val red = Color.red(oldColor_).toFloat()
        val green = Color.green(oldColor_).toFloat()
        val blue = Color.blue(oldColor_).toFloat()

        binding.fragmentRGBColorPickerRedProgressBar.value = red
        binding.fragmentRGBColorPickerGreenProgressBar.value = green
        binding.fragmentRGBColorPickerBlueProgressBar.value = blue

        binding.fragmentRGBColorPickerValueR.setText(red.toInt().toString())
        binding.fragmentRGBColorPickerValueG.setText(green.toInt().toString())
        binding.fragmentRGBColorPickerValueB.setText(blue.toInt().toString())

        binding.textInputLayout1.editText?.filters = arrayOf(InputFilterMinMax(IntConstants.RGBMin, IntConstants.RGBMax))
        binding.textInputLayout2.editText?.filters = arrayOf(InputFilterMinMax(IntConstants.RGBMin, IntConstants.RGBMax))
        binding.textInputLayout3.editText?.filters = arrayOf(InputFilterMinMax(IntConstants.RGBMin, IntConstants.RGBMax))
    }

    private fun updateColorPickerColorPreview() {
        binding.fragmentRGBColorPickerColorPreview.setBackgroundColor(Color.argb(255, valueR.toInt(), valueG.toInt(), valueB.toInt()))
    }

    private fun setOnChangeListeners() {
            binding.fragmentRGBColorPickerRedProgressBar.addOnChangeListener { _, value, _ ->
                valueR = value
                updateColorPickerColorPreview()
                binding.fragmentRGBColorPickerValueR.setText(valueR.toInt().toString())
            }

            binding.fragmentRGBColorPickerGreenProgressBar.addOnChangeListener { _, value, _ ->
                valueG = value
                updateColorPickerColorPreview()
                binding.fragmentRGBColorPickerValueG.setText(valueG.toInt().toString())
            }

            binding.fragmentRGBColorPickerBlueProgressBar.addOnChangeListener { _, value, _ ->
                valueB = value
                updateColorPickerColorPreview()
                binding.fragmentRGBColorPickerValueB.setText(valueB.toInt().toString())
            }


            binding.fragmentRGBColorPickerValueR.doAfterTextChanged {
                try {
                    valueR = binding.fragmentRGBColorPickerValueR.text.toString().toFloat()
                    valueG = binding.fragmentRGBColorPickerValueG.text.toString().toFloat()
                    valueB = binding.fragmentRGBColorPickerValueB.text.toString().toFloat()

                    updateColorPickerColorPreview()
                } catch (exception: Exception) { }
            }

            binding.fragmentRGBColorPickerValueR.setOnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            }

            binding.fragmentRGBColorPickerValueR.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }

                    return@setOnEditorActionListener true
                }
                false
            }

            binding.fragmentRGBColorPickerValueG.doAfterTextChanged {
                try {
                    valueR = binding.fragmentRGBColorPickerValueR.text.toString().toFloat()
                    valueG = binding.fragmentRGBColorPickerValueG.text.toString().toFloat()
                    valueB = binding.fragmentRGBColorPickerValueB.text.toString().toFloat()

                    updateColorPickerColorPreview()
                } catch (exception: Exception) { }
            }

            binding.fragmentRGBColorPickerValueG.setOnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            }

            binding.fragmentRGBColorPickerValueG.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }

                    return@setOnEditorActionListener true
                }
                false
            }

            binding.fragmentRGBColorPickerValueB.doAfterTextChanged {
                try {
                    valueR = binding.fragmentRGBColorPickerValueR.text.toString().toFloat()
                    valueG = binding.fragmentRGBColorPickerValueG.text.toString().toFloat()
                    valueB = binding.fragmentRGBColorPickerValueB.text.toString().toFloat()

                    updateColorPickerColorPreview()
                } catch (exception: Exception) { }
            }

            binding.fragmentRGBColorPickerValueB.setOnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            }

            binding.fragmentRGBColorPickerValueB.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }

                    return@setOnEditorActionListener true
                }
                false
            }


        keyboardVisibilityEventListenerRegistrar = registerEventListener(requireActivity(),
            KeyboardVisibilityEventListener { isOpen ->
                if (!isOpen) {
                    try {
                        binding.fragmentRGBColorPickerRedProgressBar.value = valueR
                        binding.fragmentRGBColorPickerGreenProgressBar.value = valueG
                        binding.fragmentRGBColorPickerBlueProgressBar.value = valueB
                    } catch (exception: Exception) { }
                }
            })
    }

    companion object {
        fun newInstance(): RGBColorPickerFragment {
            return RGBColorPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentRGBColorPickerBinding.inflate(inflater, container, false)

        setOnClickListeners()
        setup()
        setOnChangeListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }
}