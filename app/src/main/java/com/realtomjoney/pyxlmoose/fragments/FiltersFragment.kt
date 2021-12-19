package com.realtomjoney.pyxlmoose.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentFiltersBinding
import com.realtomjoney.pyxlmoose.databinding.FragmentToolsBinding
import com.realtomjoney.pyxlmoose.listeners.FiltersFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ToolsFragmentListener
import com.realtomjoney.pyxlmoose.utility.StringConstants


class FiltersFragment : Fragment() {
    private var _binding: FragmentFiltersBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: FiltersFragmentListener

    private fun setOnClickListeners() {
        binding.apply {
            fragmentFiltersColorFilterButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.COLOR_FILTER_IDENTIFIER)
            }

            fragmentFiltersLightenButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.LIGHTEN_FILTER_IDENTIFIER)
            }

            fragmentFiltersDarkenButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.DARKEN_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.INVERT_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertRedButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.INVERT_RED_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertGreenButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.INVERT_GREEN_FILTER_IDENTIFIER)
            }

            fragmentFiltersInvertBlueButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.INVERT_BLUE_FILTER_IDENTIFIER)
            }

            fragmentFiltersGrayScaleButton.setOnClickListener {
                caller.onFilterSelected(StringConstants.GRAYSCALE_FILTER_IDENTIFIER)
            }

            fragmentFiltersGrayScaleButtonTwo.setOnClickListener {
                caller.onFilterSelected(StringConstants.GRAYSCALE_FILTER_TWO_IDENTIFIER)
            }

            fragmentFiltersGrayScaleButtonThree.setOnClickListener {
                caller.onFilterSelected(StringConstants.GRAYSCALE_FILTER_THREE_IDENTIFIER)
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
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}