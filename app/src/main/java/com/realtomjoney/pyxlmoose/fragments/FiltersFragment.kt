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


class FiltersFragment : Fragment() {
    private var _binding: FragmentFiltersBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: FiltersFragmentListener

    companion object {
        fun newInstance() = FiltersFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FiltersFragmentListener) caller = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)

        binding.fragmentFiltersColorFilterButton.setOnClickListener {
            caller.onFilterSelected("COLOR_FILTER")
        }

        binding.fragmentFiltersLightenButton.setOnClickListener {
            caller.onFilterSelected("LIGHTEN_FILTER")
        }

        binding.fragmentFiltersDarkenButton.setOnClickListener {
            caller.onFilterSelected("DARKEN_FILTER")
        }

        binding.fragmentFiltersInvertButton.setOnClickListener {
            caller.onFilterSelected("INVERT_FILTER")
        }

        binding.fragmentFiltersInvertRedButton.setOnClickListener {
            caller.onFilterSelected("INVERT_RED_FILTER")
        }

        binding.fragmentFiltersInvertGreenButton.setOnClickListener {
            caller.onFilterSelected("INVERT_GREEN_FILTER")
        }

        binding.fragmentFiltersInvertBlueButton.setOnClickListener {
            caller.onFilterSelected("INVERT_BLUE_FILTER")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}