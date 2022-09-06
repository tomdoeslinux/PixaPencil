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

package com.therealbluepandabear.pixapencil.fragments.colorpicker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.ColorPickerFragmentListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

class ColorPickerFragment : Fragment(), ActivityFragment {
    private var paramOldColor: Int? = null
    private var paramColorPalette: ColorPalette? = null

    override val title: String by lazy { getString(R.string.fragment_color_picker_title) }

    fun setParams(paramOldColor: Int, paramColorPalette: ColorPalette?) {
        this.paramOldColor = paramOldColor
        this.paramColorPalette = paramColorPalette
    }

    private fun instantiateVariables() {
        if (paramOldColor != null) {
            oldColor_ = paramOldColor!!
            colorPalette = paramColorPalette
        }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.fragmentColorPickerViewPager2.adapter = adapter
        binding.fragmentColorPickerViewPager2.offscreenPageLimit = 3
        binding.fragmentColorPickerViewPager2.isUserInputEnabled = false
        binding.fragmentColorPickerTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.fragmentColorPickerViewPager2.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })
    }

    private fun setup() {
        instantiateVariables()
        setupViewPager()
    }

    companion object {
        fun newInstance(paramOldColor: Int, paramColorPalette: ColorPalette? = null): ColorPickerFragment {
            val fragment = ColorPickerFragment()
            fragment.setParams(paramOldColor, paramColorPalette)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentColorPickerBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            prevColorPickerTab = savedInstanceState.getInt(StringConstants.Identifiers.PREV_COLOR_PICKER_TAB_BUNDLE_IDENTIFIER)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.fragmentColorPickerTabLayout.getTabAt(binding.fragmentColorPickerViewPager2.currentItem)?.select()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}