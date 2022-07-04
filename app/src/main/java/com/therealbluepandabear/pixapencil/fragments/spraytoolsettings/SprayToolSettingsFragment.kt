package com.therealbluepandabear.pixapencil.fragments.spraytoolsettings

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

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentSprayToolSettingsBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.SprayToolSettingsFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

class SprayToolSettingsFragment : Fragment(), ActivityFragment {
    lateinit var paramSharedPreferenceObject: SharedPreferences

    private var backingBindingProperty: FragmentSprayToolSettingsBinding? = null

    val binding get(): FragmentSprayToolSettingsBinding {
        return backingBindingProperty!!
    }

    lateinit var caller: SprayToolSettingsFragmentListener

    override val title: String by lazy { getString(R.string.fragment_spray_tool_settings_title_in_code_str) }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentSprayToolSettingsBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}