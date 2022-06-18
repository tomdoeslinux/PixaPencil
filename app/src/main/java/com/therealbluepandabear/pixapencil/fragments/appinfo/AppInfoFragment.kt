package com.therealbluepandabear.pixapencil.fragments.appinfo

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentAppInfoBinding.inflate(inflater, container, false)

        activity?.findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView)?.visibility = View.GONE

        binding.fragmentAppInfoAppVersion.text = BuildConfig.VERSION_NAME
        binding.fragmentAppInfoCommunityText.movementMethod = LinkMovementMethod.getInstance()
        binding.fragmentAppInfoCreatedByText.movementMethod = LinkMovementMethod.getInstance()
        binding.fragmentAppInfoAboutText.movementMethod = LinkMovementMethod.getInstance()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }

    companion object {
        fun newInstance(): AppInfoFragment {
            return AppInfoFragment()
        }
    }
}