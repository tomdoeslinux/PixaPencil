package com.therealbluepandabear.pyxlmoose.fragments.colorpalettes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.databinding.FragmentColorPalettesBinding
import com.therealbluepandabear.pyxlmoose.enums.SnackbarDuration
import com.therealbluepandabear.pyxlmoose.extensions.showDialog
import com.therealbluepandabear.pyxlmoose.extensions.showSnackbar
import com.therealbluepandabear.pyxlmoose.listeners.ColorPalettesFragmentListener
import com.therealbluepandabear.pyxlmoose.listeners.ColorPalettesListener
import com.therealbluepandabear.pyxlmoose.models.ColorPalette
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

class ColorPalettesFragment : Fragment(), ColorPalettesListener {
    val context = this

    private fun deleteColorPaletteAndNotifyItemRemoved(colorPalette: ColorPalette) {
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
            val colorPaletteId = colorPalette.objId

            AppData.colorPalettesDB.colorPalettesDao().deleteColorPalette(colorPaletteId)
            binding.fragmentColorPalettesRecyclerView.adapter!!.notifyItemRemoved(it.indexOf(colorPalette))
        }
    }

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
        val name = selectedColorPalette.colorPaletteName

        if (!selectedColorPalette.isPrimaryColorPalette) {
            requireActivity().showDialog(
                "Delete '$name'?",
                "Are you sure you want to delete '$name'? - this cannot be undone.",
                StringConstants.DialogPositiveButtonText, { _, _ ->
                    deleteColorPaletteAndNotifyItemRemoved(selectedColorPalette)
                }, "Cancel", null, null
            )
        } else {
            binding.fragmentColorPalettesRootLayout.showSnackbar(StringConstants.SnackbarCannotDeletePrimaryColorPaletteText, SnackbarDuration.Default)
        }
    }
}