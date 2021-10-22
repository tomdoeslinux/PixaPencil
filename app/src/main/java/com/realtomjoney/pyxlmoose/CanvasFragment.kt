package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding

class CanvasFragment : Fragment() {
    private var _binding: FragmentCanvasBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: CanvasFragmentListener

    companion object {
        fun newInstance(): CanvasFragment {
            return CanvasFragment()
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
        binding.canvasRecyclerView.layoutManager = GridLayoutManager(context, 25)
        val pixels = caller.initPixels()
        binding.canvasRecyclerView.adapter = CanvasRecyclerAdapter(pixels, caller)
        binding.canvasRecyclerView.suppressLayout(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}