/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentSprayToolSettingsBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.SprayToolSettingsFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

class SprayToolSettingsFragment : Fragment(), ActivityFragment {
    lateinit var paramSharedPreferenceObject: SharedPreferences

    private var _binding: FragmentSprayToolSettingsBinding? = null

    val binding get(): FragmentSprayToolSettingsBinding {
        return _binding!!
    }

    lateinit var caller: SprayToolSettingsFragmentListener

    override val title: String by lazy { getString(R.string.fragment_spray_tool_settings_title) }

    fun setParams(paramSharedPreferenceObject: SharedPreferences) {
        this.paramSharedPreferenceObject = paramSharedPreferenceObject
    }

    private fun setDefaultValues() {
        val sprayRadiusSharedPreference = paramSharedPreferenceObject.getInt(StringConstants.Identifiers.SHARED_PREFERENCE_SPRAY_RADIUS_IDENTIFIER, IntConstants.SprayRadius)
        val sprayStrengthSharedPreference = paramSharedPreferenceObject.getInt(StringConstants.Identifiers.SHARED_PREFERENCE_SPRAY_STRENGTH_IDENTIFIER, IntConstants.SprayStrength)

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
        fun newInstance(paramSharedPreferenceObject: SharedPreferences): SprayToolSettingsFragment {
            val fragment = SprayToolSettingsFragment()
            fragment.setParams(paramSharedPreferenceObject)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SprayToolSettingsFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSprayToolSettingsBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}