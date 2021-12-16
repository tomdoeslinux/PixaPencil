package com.realtomjoney.pyxlmoose.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentToolsBinding
import com.realtomjoney.pyxlmoose.listeners.ToolsFragmentListener


class ToolsFragment : Fragment() {
    private var _binding: FragmentToolsBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: ToolsFragmentListener

    companion object {
        fun newInstance() = ToolsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolsFragmentListener) caller = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToolsBinding.inflate(inflater, container, false)

        binding.apply {
            fragmentToolsPencilButton.setOnClickListener { caller.onToolTapped("PENCIL") }
            fragmentToolsVerticalMirrorButton.setOnClickListener { caller.onToolTapped("VERTICAL_MIRROR") }
            fragmentToolsHorizontalMirrorButton.setOnClickListener { caller.onToolTapped("HORIZONTAL_MIRROR") }
            fragmentToolsDarkenButton.setOnClickListener { caller.onToolTapped("DARKEN") }
            fragmentToolsLightenButton.setOnClickListener { caller.onToolTapped("LIGHTEN") }
            fragmentToolsResetCanvasButton.setOnClickListener { caller.onToolTapped("RESET_CANVAS") }
            fragmentToolsChangeBackgroundButton.setOnClickListener { caller.onToolTapped("CHANGE_BACKGROUND") }
            fragmentToolsColorPickerButton.setOnClickListener { caller.onToolTapped("COLOR_PICKER") }
            fragmentToolsFindAndReplaceButton.setOnClickListener { caller.onToolTapped("FIND_AND_REPLACE") }
            fragmentToolsEraseButton.setOnClickListener { caller.onToolTapped("ERASE") }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}