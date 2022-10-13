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

package com.therealbluepandabear.pixapencil.fragments.newproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.FragmentNewProjectBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener

class NewProjectFragment : Fragment(), ActivityFragment {
    private var _binding: FragmentNewProjectBinding? = null

    val binding get(): FragmentNewProjectBinding {
        return _binding!!
    }

    lateinit var caller: NewProjectFragmentListener

    override val title: String by lazy { getString(R.string.fragment_new_project_title) }

    private fun setup() {
        activity?.findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView)?.visibility = View.GONE
        setOnClickListeners()
    }

    companion object {
        fun newInstance(): NewProjectFragment {
            return NewProjectFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewProjectFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.STARTED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewProjectBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = getString(R.string.app_name)
    }
}