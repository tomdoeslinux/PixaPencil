package com.therealbluepandabear.pixapencil.fragments.newproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.FragmentNewProjectBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener

class NewProjectFragment : Fragment(), ActivityFragment {
    private var _binding: FragmentNewProjectBinding? = null

    val binding get(): FragmentNewProjectBinding {
        return _binding!!
    }

    lateinit var caller: NewProjectFragmentListener

    override val title: String by lazy { getString(R.string.fragment_new_project_title) }

    var paramSpotLightInProgress: Boolean = false

    fun setParams(paramSpotLightInProgress: Boolean) {
        this.paramSpotLightInProgress = paramSpotLightInProgress
    }

    private fun setup() {
        activity?.findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView)?.visibility = View.GONE
        setOnClickListeners()
    }

    companion object {
        fun newInstance(paramSpotLightInProgress: Boolean = false): NewProjectFragment {
            val fragment = NewProjectFragment()
            fragment.setParams(paramSpotLightInProgress)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewProjectFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.STARTED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewProjectBinding.inflate(inflater, container, false)

        setup()

        if (paramSpotLightInProgress) {
            binding.fragmentNewCanvasProjectTitleTextInputLayout.doOnPreDraw {
                startSpotLight()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = getString(R.string.app_name)
    }
}