package com.realtomjoney.pyxlmoose.fragments.spraytoolsettings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentSprayToolSettingsBinding
import com.realtomjoney.pyxlmoose.listeners.SprayToolSettingsFragmentListener
import com.realtomjoney.pyxlmoose.utility.IntConstants

class SprayToolSettingsFragment : Fragment() {
    private fun setDefaultValues() {
        binding.fragmentSprayToolSettingsRadiusTextInputEditText.setText(IntConstants.SprayRadius.toString())
        binding.fragmentSprayToolSettingsStrengthTextInputEditText.setText(IntConstants.SprayStrength.toString())
    }

    private fun setup() {
        setDefaultValues()
        setOnClickListeners()
    }

    companion object {
        fun newInstance(): SprayToolSettingsFragment {
            return SprayToolSettingsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SprayToolSettingsFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentSprayToolSettingsBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}