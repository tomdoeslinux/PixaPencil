package com.therealbluepandabear.pixapencil.fragments.filters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentFiltersBinding
import com.therealbluepandabear.pixapencil.listeners.FiltersFragmentListener

class FiltersFragment : Fragment() {
    private var backingBindingProperty: FragmentFiltersBinding? = null

    val binding get(): FragmentFiltersBinding {
        return backingBindingProperty!!
    }

    lateinit var caller: FiltersFragmentListener

    companion object {
        fun newInstance(): FiltersFragment {
            return FiltersFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FiltersFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentFiltersBinding.inflate(inflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }

}