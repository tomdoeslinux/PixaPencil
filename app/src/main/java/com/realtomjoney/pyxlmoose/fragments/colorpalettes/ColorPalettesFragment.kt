package com.realtomjoney.pyxlmoose.fragments.colorpalettes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPalettesBinding
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.listeners.ColorPalettesFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColorPalettesListener
import com.realtomjoney.pyxlmoose.models.ColorPalette
import com.realtomjoney.pyxlmoose.utility.StringConstants

class ColorPalettesFragment(val lifecycleOwner: LifecycleOwner) : Fragment(), ColorPalettesListener {
    val context = this

    private fun deleteColorPaletteAndNotifyItemRemoved(colorPalette: ColorPalette) {
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
            val colorPaletteId = colorPalette.objId

            AppData.colorPalettesDB.colorPalettesDao().deleteColorPalette(colorPaletteId)
            binding.fragmentColorPalettesRecyclerView.adapter!!.notifyItemRemoved(it.indexOf(colorPalette))
        }
    }

    companion object {
        fun newInstance(lifecycleOwner: LifecycleOwner) = ColorPalettesFragment(lifecycleOwner)
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
        if (!selectedColorPalette.isPrimaryColorPalette) {
            deleteColorPaletteAndNotifyItemRemoved(selectedColorPalette)
        } else {
            binding.fragmentColorPalettesRootLayout.showSnackbar(StringConstants.SNACKBAR_CANNOT_DELETE_PRIMARY_COLOR_PALETTE_TEXT, SnackbarDuration.DEFAULT)
        }
    }
}