package com.realtomjoney.pyxlmoose.fragments.canvas

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.activities.canvas.canvasFragmentInstance
import com.realtomjoney.pyxlmoose.activities.canvas.index
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView


class CanvasFragment(private val spanCount: Int) : Fragment() {
    private var _binding: FragmentCanvasBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: CanvasFragmentListener

    lateinit var myCanvasViewInstance: MyCanvasView

    private fun setupCanvas() {
        myCanvasViewInstance = MyCanvasView(requireContext(), spanCount.toDouble())

        binding.fragmentCanvasRootLayout.addView(myCanvasViewInstance)

        if (index != -1) canvasFragmentInstance.myCanvasViewInstance.loadData(this, index!!)
    }

    companion object {
        fun newInstance(spanCount: Int) = CanvasFragment(spanCount)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CanvasFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCanvasBinding.inflate(inflater, container, false)

        setupCanvas()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}