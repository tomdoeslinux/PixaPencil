package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.extendedOnCreationLongTapped
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPalettesBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesFragmentListener
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.utility.ObjectConstants
import kotlinx.coroutines.selects.select

class ColorPalettesFragment : Fragment(), ColorPalettesListener {
    val context = this

    companion object {
        fun newInstance(): ColorPalettesFragment {
            return ColorPalettesFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ColorPalettesFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPalettesBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        caller.onColorPaletteTapped(selectedColorPalette)
    }

    override fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette) {
        caller.onColorPaletteLongTapped(selectedColorPalette)
    }
}