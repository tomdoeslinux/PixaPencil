package com.realtomjoney.pyxlmoose.fragments.colorpalettes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.ColorPalettesAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPalettesBinding
import com.realtomjoney.pyxlmoose.listeners.ColorPalettesFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColorPalettesListener
import com.realtomjoney.pyxlmoose.models.ColorPalette

class ColorPalettesFragment(private val lifecycleOwner: LifecycleOwner) : Fragment(), ColorPalettesListener {
    private fun setUpRecyclerView() {
        binding.apply {
            fragmentColorPalettesRecyclerView.layoutManager = LinearLayoutManager(this@ColorPalettesFragment.activity).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(lifecycleOwner) {
                fragmentColorPalettesRecyclerView.adapter = ColorPalettesAdapter(it, this@ColorPalettesFragment)
            }
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
            AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this, {
                AppData.colorPalettesDB.colorPalettesDao().deleteColorPalette(selectedColorPalette.objId)
                binding.fragmentColorPalettesRecyclerView.adapter!!.notifyItemRemoved(it.indexOf(selectedColorPalette))
            })
        }
    }
}