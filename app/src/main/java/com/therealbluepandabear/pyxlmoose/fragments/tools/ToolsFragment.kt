package com.therealbluepandabear.pyxlmoose.fragments.tools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pyxlmoose.activities.canvas.currentTool
import com.therealbluepandabear.pyxlmoose.databinding.FragmentToolsBinding
import com.therealbluepandabear.pyxlmoose.enums.Tools
import com.therealbluepandabear.pyxlmoose.listeners.ToolsFragmentListener

class ToolsFragment : Fragment() {
    private fun setup() {
        currentTool = Tools.PencilTool
        currentlySelectedFAB = binding.fragmentToolsPencilButton
        currentlySelectedFAB = binding.fragmentToolsPencilButtonH

        setColorFor(binding.fragmentToolsPencilButton)
        setColorFor(binding.fragmentToolsPencilButtonH)
        setOnClickListeners()
    }

    companion object {
        fun newInstance(): ToolsFragment {
            return ToolsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolsFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentToolsBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

}