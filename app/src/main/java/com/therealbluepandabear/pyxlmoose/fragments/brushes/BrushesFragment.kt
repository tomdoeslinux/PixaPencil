package com.therealbluepandabear.pyxlmoose.fragments.brushes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentBrushesBinding
import com.therealbluepandabear.pyxlmoose.listeners.BrushesFragmentListener
import com.therealbluepandabear.pyxlmoose.listeners.BrushesListener
import com.therealbluepandabear.pyxlmoose.models.Brush

class BrushesFragment : Fragment(), BrushesListener {
    companion object {
        fun newInstance(): BrushesFragment {
            return BrushesFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BrushesFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentBrushesBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

    override fun onBrushTapped(selectedBrush: Brush) {
        caller.onBrushTapped(selectedBrush)
    }
}