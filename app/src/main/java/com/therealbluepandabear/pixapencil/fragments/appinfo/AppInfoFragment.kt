package com.therealbluepandabear.pixapencil.fragments.appinfo

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.therealbluepandabear.pixapencil.BuildConfig
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.FragmentAppInfoBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment

class AppInfoFragment : Fragment() {
    private var _binding: FragmentAppInfoBinding? = null

    val binding get(): FragmentAppInfoBinding {
        return _binding!!
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.children.forEach {
                    it.isVisible = false
                }
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAppInfoBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}