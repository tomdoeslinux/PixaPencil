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