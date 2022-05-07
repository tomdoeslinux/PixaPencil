package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.FragmentNewColorPaletteBinding
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.NewColorPaletteFragmentListener

class NewColorPaletteFragment : Fragment(), ActivityFragment {
    override val title: String by lazy { getString(R.string.fragment_new_color_palette_title_in_code_str) }

    companion object {
        fun newInstance(): NewColorPaletteFragment {
            return NewColorPaletteFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewColorPaletteFragmentListener) caller = context
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
        binding_ = FragmentNewColorPaletteBinding.inflate(inflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}