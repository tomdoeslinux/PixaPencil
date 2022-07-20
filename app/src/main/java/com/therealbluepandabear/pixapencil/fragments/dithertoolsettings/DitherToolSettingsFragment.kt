package com.therealbluepandabear.pixapencil.fragments.dithertoolsettings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentDitherToolSettingsBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.DitherBrushesListener
import com.therealbluepandabear.pixapencil.listeners.DitherToolSettingsFragmentListener
import com.therealbluepandabear.pixapencil.models.DitherBrush

class DitherToolSettingsFragment : Fragment(), ActivityFragment, DitherBrushesListener {
    override val title: String by lazy { getString(R.string.fragment_dither_tool_settings_title_in_code_str) }

    private var _binding: FragmentDitherToolSettingsBinding? = null

    val binding get(): FragmentDitherToolSettingsBinding {
        return _binding!!
    }

    lateinit var caller: DitherToolSettingsFragmentListener

    companion object {
        fun newInstance(): DitherToolSettingsFragment {
            return DitherToolSettingsFragment()
        }
    }

    override fun onDitherBrushTapped(ditherBrush: DitherBrush) {
        caller.onDitherBrushTapped(ditherBrush)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DitherToolSettingsFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDitherToolSettingsBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}