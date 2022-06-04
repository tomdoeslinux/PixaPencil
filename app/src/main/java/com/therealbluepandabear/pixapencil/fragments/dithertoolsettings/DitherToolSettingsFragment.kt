package com.therealbluepandabear.pixapencil.fragments.dithertoolsettings

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentDitherToolSettingsBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.DitherBrushesListener
import com.therealbluepandabear.pixapencil.listeners.DitherToolSettingsFragmentListener
import com.therealbluepandabear.pixapencil.models.DitherBrush

class DitherToolSettingsFragment : Fragment(), ActivityFragment, DitherBrushesListener {
    override val title: String by lazy { "Dither Tool Settings" }

    private var backingBindingProperty: FragmentDitherToolSettingsBinding? = null

    val binding get(): FragmentDitherToolSettingsBinding {
        return backingBindingProperty!!
    }

    lateinit var caller: DitherToolSettingsFragmentListener

    companion object {
        fun newInstance(): DitherToolSettingsFragment {
            return DitherToolSettingsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DitherToolSettingsFragmentListener) caller = context
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
        backingBindingProperty = FragmentDitherToolSettingsBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }

    override fun onDitherBrushTapped(ditherBrush: DitherBrush) {
        caller.onDitherBrushTapped(ditherBrush)
    }
}