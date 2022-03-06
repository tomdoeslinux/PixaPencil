package com.realtomjoney.pyxlmoose.fragments.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.customviews.pixelgridview.PixelGridView
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding

class CanvasFragment(var width: Int, var height: Int, private val isEmpty: Boolean = false) : Fragment() {
    lateinit var myCanvasViewInstance: PixelGridView

    var bitmap: Bitmap? = null

    private fun setupCanvas() {
        myCanvasViewInstance = PixelGridView(requireContext(), width, height, isEmpty)
        binding.fragmentCanvasRootLayout.addView(myCanvasViewInstance)
    }

    companion object {
        fun newInstance(width: Int, height: Int, isEmpty: Boolean = false) = CanvasFragment(width, height, isEmpty)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentCanvasBinding.inflate(inflater, container, false)

        setupCanvas()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}