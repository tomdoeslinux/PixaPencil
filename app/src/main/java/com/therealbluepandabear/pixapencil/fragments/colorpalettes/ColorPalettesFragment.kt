package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPalettesBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesFragmentListener
import com.therealbluepandabear.pixapencil.listeners.ColorPalettesListener
import com.therealbluepandabear.pixapencil.models.ColorPalette

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
                getString(R.string.dialog_positive_button_text_in_code_str), { _, _ ->
                    deleteColorPaletteAndNotifyItemRemoved(selectedColorPalette)
                }, getString(R.string.dialog_negative_button_text_in_code_str), null, null
            )
        } else {
            binding.fragmentColorPalettesRootLayout.showSnackbar(getString(R.string.snackbar_cannot_delete_primary_color_palette_text_in_code_str), SnackbarDuration.Default)
        }
    }
}