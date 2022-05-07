package com.therealbluepandabear.pixapencil.fragments.newproject

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.FragmentNewProjectBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener
import com.therealbluepandabear.pixapencil.utility.StringConstants

class NewProjectFragment : Fragment(), ActivityFragment {
    var root: View? = null

    override val title: String by lazy { getString(R.string.fragment_new_project_title_in_code_str) }

    private fun instantiateRoot() {
        root = binding.fragmentNewCanvasRootLayout
    }

    private fun setup() {
        instantiateRoot()
        setOnClickListeners()
    }

    companion object {
        fun newInstance(): NewProjectFragment {
            return NewProjectFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewProjectFragmentListener) caller = context
        requireActivity().findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView).visibility = View.GONE
        requireActivity().title = this.title
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
        binding_ = FragmentNewProjectBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
        requireActivity().findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView).visibility = View.VISIBLE
        requireActivity().title = StringConstants.AppName
    }
}