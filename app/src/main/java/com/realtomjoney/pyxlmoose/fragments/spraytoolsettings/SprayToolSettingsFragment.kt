package com.realtomjoney.pyxlmoose.fragments.spraytoolsettings

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentSprayToolSettingsBinding
import com.realtomjoney.pyxlmoose.extensions.hideKeyboard
import com.realtomjoney.pyxlmoose.listeners.SprayToolSettingsFragmentListener
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.LongConstants

class SprayToolSettingsFragment : Fragment() {
    companion object {
        fun newInstance() = SprayToolSettingsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SprayToolSettingsFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentSprayToolSettingsBinding.inflate(inflater, container, false)

        binding.fragmentSprayToolSettingsRadiusTextInputEditText.setText(IntConstants.DEF_RADIUS_SIZE.toString())
        binding.fragmentSprayToolSettingsStrengthTextInputEditText.setText(IntConstants.DEF_STRENGTH_SIZE.toString())

        binding.fragmentSprayToolSettingsDoneButton.setOnClickListener {
            val radius = binding.fragmentSprayToolSettingsRadiusTextInputEditText.text.toString()
            val strength = binding.fragmentSprayToolSettingsStrengthTextInputEditText.text.toString()

            hideKeyboard()

            Handler(Looper.getMainLooper()).postDelayed({
                caller.onDoneButtonPressed(radius, strength)
            }, LongConstants.DEF_HANDLER_DELAY)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}