package com.realtomjoney.pyxlmoose.fragments.filters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentFiltersBinding
import com.realtomjoney.pyxlmoose.listeners.FiltersFragmentListener
import com.realtomjoney.pyxlmoose.utility.StringConstants


class FiltersFragment : Fragment() {
    private fun setOnClickListeners() {
        binding.apply {
            fragmentFiltersColorFilterButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.COLOR_FILTER_IDENTIFIER)
            }

            fragmentFiltersLightenButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.LIGHTEN_FILTER_IDENTIFIER)
            }

            fragmentFiltersDarkenButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.DARKEN_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.INVERT_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertRedButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.INVERT_RED_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertGreenButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.INVERT_GREEN_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertBlueButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.INVERT_BLUE_FILTER_IDENTIFIER)
            }

            fragmentFiltersGrayScaleButton.setOnClickListener {
                caller.onFilterTapped(StringConstants.GRAYSCALE_FILTER_IDENTIFIER)
            }

            fragmentFiltersGrayScaleButtonTwo.setOnClickListener {
                caller.onFilterTapped(StringConstants.GRAYSCALE_FILTER_TWO_IDENTIFIER)
            }

            fragmentFiltersGrayScaleButtonThree.setOnClickListener {
                caller.onFilterTapped(StringConstants.GRAYSCALE_FILTER_THREE_IDENTIFIER)
            }
        }
    }

    companion object {
        fun newInstance() = FiltersFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FiltersFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentFiltersBinding.inflate(inflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

}