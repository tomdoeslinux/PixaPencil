package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.databinding.FragmentSprayToolSettingsBinding
import com.therealbluepandabear.pixapencil.listeners.SprayToolSettingsFragmentListener
import com.therealbluepandabear.pixapencil.utility.IntConstants
import com.therealbluepandabear.pixapencil.utility.StringConstants

class SprayToolSettingsFragment : Fragment() {
    private fun setDefaultValues() {
        val sprayRadiusSharedPreference = sharedPreferenceObject.getInt(StringConstants.Identifiers.SharedPreferencesSprayRadiusIdentifier, IntConstants.SprayRadius)
        val sprayStrengthSharedPreference = sharedPreferenceObject.getInt(StringConstants.Identifiers.SharedPreferencesSprayStrengthIdentifier, IntConstants.SprayStrength)

        val sprayRadiusSharedPreferenceStr = sprayRadiusSharedPreference.toString()
        val sprayStrengthSharedPreferenceStr = sprayStrengthSharedPreference.toString()

        binding.fragmentSprayToolSettingsRadiusTextInputEditText.setText(sprayRadiusSharedPreferenceStr)
        binding.fragmentSprayToolSettingsStrengthTextInputEditText.setText(sprayStrengthSharedPreferenceStr)
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
        requireActivity().title = getString(R.string.fragment_spray_tool_settings_title_in_code_str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentSprayToolSettingsBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}