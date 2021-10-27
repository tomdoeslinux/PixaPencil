package com.realtomjoney.pyxlmoose

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding

class CanvasFragment(private val spanCount: Int, private val isGridVisible: Boolean) : Fragment() {
    private var _binding: FragmentCanvasBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: CanvasFragmentListener

    companion object {
        fun newInstance(spanCount: Int, isGridVisible: Boolean): CanvasFragment {
            return CanvasFragment(spanCount, isGridVisible)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CanvasFragmentListener) {
            caller = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCanvasBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val context = activity as Context
        binding.canvasRecyclerView.layoutManager = GridLayoutManager(context, spanCount)
        val pixels = caller.initPixels()
        binding.canvasRecyclerView.adapter = CanvasRecyclerAdapter(pixels, caller, isGridVisible)
        binding.canvasRecyclerView.suppressLayout(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}