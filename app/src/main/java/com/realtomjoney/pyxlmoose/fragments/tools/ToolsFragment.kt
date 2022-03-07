package com.realtomjoney.pyxlmoose.fragments.tools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentToolsBinding
import com.realtomjoney.pyxlmoose.listeners.ToolsFragmentListener

class ToolsFragment : Fragment() {
    private fun setup() {
        setColorFor(binding.fragmentToolsPencilButton)
        setDefaultSelectedFAB()
        setOnClickListeners()
    }

    private fun setDefaultSelectedFAB() {
        currentlySelectedFAB = binding.fragmentToolsPencilButton
    }

    companion object {
        fun newInstance() = ToolsFragment()
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