package com.therealbluepandabear.pixapencil.fragments.newproject

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.FragmentNewProjectBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener

class NewProjectFragment : Fragment(), ActivityFragment {
    private var backingBindingProperty: FragmentNewProjectBinding? = null

    val binding get(): FragmentNewProjectBinding {
        return backingBindingProperty!!
    }

    lateinit var caller: NewProjectFragmentListener

    override val title: String by lazy { getString(R.string.fragment_new_project_title_in_code_str) }

    var paramSpotLightInProgress: Boolean = false

    fun setParams(paramSpotLightInProgress: Boolean) {
        this.paramSpotLightInProgress = paramSpotLightInProgress
    }

    private fun setup() {
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
        requireActivity().findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView).visibility = View.GONE
        requireActivity().title = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentNewProjectBinding.inflate(inflater, container, false)

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
        backingBindingProperty = null
        requireActivity().findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView).visibility = View.VISIBLE
        requireActivity().title = getString(R.string.app_name)
    }
}