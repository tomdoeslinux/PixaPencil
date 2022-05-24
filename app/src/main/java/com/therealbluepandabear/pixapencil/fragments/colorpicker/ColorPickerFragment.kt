package com.therealbluepandabear.pixapencil.fragments.colorpicker

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.ColorPickerFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

class ColorPickerFragment : Fragment(), ActivityFragment {
    private var paramOldColor: Int? = null
    private var paramColorPaletteMode: Boolean = false

    override val title: String by lazy { getString(R.string.fragment_color_picker_title_in_code_str) }

    fun setParams(paramOldColor: Int, paramColorPaletteMode: Boolean) {
        this.paramOldColor = paramOldColor
        this.paramColorPaletteMode = paramColorPaletteMode
    }

    private fun instantiateVariables() {
        if (paramOldColor != null) {
            oldColor_ = paramOldColor!!
        }
        colorPaletteMode_ = paramColorPaletteMode
    }

    private fun setup() {
        instantiateVariables()
        setOnClickListeners()
    }

    companion object {
        fun newInstance(paramOldColor: Int, paramColorPaletteMode: Boolean = false): ColorPickerFragment {
            val fragment = ColorPickerFragment()
            fragment.setParams(paramOldColor, paramColorPaletteMode)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPickerFragmentListener) caller = context
        requireActivity().title = this.title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (savedInstanceState != null) {
            prevColorPickerTab = savedInstanceState.getInt(StringConstants.Identifiers.PrevColorPickerTabBundleIdentifier)
        }
    }

    override fun onStart() {
        super.onStart()

        if (prevColorPickerTab != 0) {
            binding.fragmentColorPickerTabLayout.getTabAt(prevColorPickerTab)?.select()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(
            StringConstants.Identifiers.PrevColorPickerTabBundleIdentifier,
            currentTab
        )

        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}