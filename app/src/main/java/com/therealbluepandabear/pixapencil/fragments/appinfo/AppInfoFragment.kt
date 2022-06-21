package com.therealbluepandabear.pixapencil.fragments.appinfo

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

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.therealbluepandabear.pixapencil.BuildConfig
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.FragmentAppInfoBinding

class AppInfoFragment : Fragment() {
    private var backingBindingProperty: FragmentAppInfoBinding? = null

    val binding get(): FragmentAppInfoBinding {
        return backingBindingProperty!!
    }

    private fun setup() {
        activity?.findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView)?.visibility = View.GONE

        binding.fragmentAppInfoAppVersion.text = BuildConfig.VERSION_NAME
        binding.fragmentAppInfoCommunityText.movementMethod = LinkMovementMethod.getInstance()
        binding.fragmentAppInfoCreatedByText.movementMethod = LinkMovementMethod.getInstance()
        binding.fragmentAppInfoAboutText.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        fun newInstance(): AppInfoFragment {
            return AppInfoFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentAppInfoBinding.inflate(inflater, container, false)

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
    }
}