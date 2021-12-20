package com.realtomjoney.pyxlmoose.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentToolsBinding
import com.realtomjoney.pyxlmoose.listeners.ToolsFragmentListener
import com.realtomjoney.pyxlmoose.utility.StringConstants

class ToolsFragment : Fragment() {
    private var _binding: FragmentToolsBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: ToolsFragmentListener

    private fun setOnClickListeners() {
        binding.apply {
            fragmentToolsPencilButton.setOnClickListener {
                caller.onToolTapped(StringConstants.PENCIL_TOOL_IDENTIFIER)
            }

            fragmentToolsVerticalMirrorButton.setOnClickListener {
                caller.onToolTapped(StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER)
            }

            fragmentToolsHorizontalMirrorButton.setOnClickListener {
                caller.onToolTapped(StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER)
            }

            fragmentToolsDarkenButton.setOnClickListener {
                caller.onToolTapped(StringConstants.DARKEN_TOOL_IDENTIFIER)
            }

            fragmentToolsLightenButton.setOnClickListener {
                caller.onToolTapped(StringConstants.LIGHTEN_TOOL_IDENTIFIER)
            }

            fragmentToolsResetCanvasButton.setOnClickListener {
                caller.onToolTapped(StringConstants.CLEAR_CANVAS_TOOL_IDENTIFIER)
            }

            fragmentToolsChangeBackgroundButton.setOnClickListener {
                caller.onToolTapped(StringConstants.CHANGE_BACKGROUND_TOOL_IDENTIFIER)
            }

            fragmentToolsColorPickerButton.setOnClickListener {
                caller.onToolTapped(StringConstants.COLOR_PICKER_TOOL_IDENTIFIER)
            }

            fragmentToolsFindAndReplaceButton.setOnClickListener {
                caller.onToolTapped(StringConstants.FIND_AND_REPLACE_TOOL_IDENTIFIER)
            }

            fragmentToolsEraseButton.setOnClickListener {
                caller.onToolTapped(StringConstants.ERASE_TOOL_IDENTIFIER)
            }

            fragmentToolsGridButton.setOnClickListener {
                caller.onToolTapped(StringConstants.GRID_TOOL_IDENTIFIER)
            }
        }
    }

    companion object {
        fun newInstance() = ToolsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolsFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentToolsBinding.inflate(inflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}