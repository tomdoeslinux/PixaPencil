package com.therealbluepandabear.pixapencil.fragments.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.customviews.pixelgridview.PixelGridView
import com.therealbluepandabear.pixapencil.databinding.FragmentCanvasBinding
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.utility.IntConstants

lateinit var pixelGridViewInstance: PixelGridView

class CanvasFragment(private val outerCanvasInstance: OuterCanvasFragment) : Fragment() {
    var bitmap: Bitmap? = null

    private var paramWidth: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramHeight: Int = IntConstants.DefaultCanvasWidthHeight

    fun setParams(paramWidth: Int, paramHeight: Int) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
    }

    private fun setupCanvas() {
        pixelGridViewInstance = PixelGridView(requireContext(), paramWidth, paramHeight, outerCanvasInstance)
        binding.fragmentCanvasRootLayout.addView(pixelGridViewInstance)
    }

    companion object {
        fun newInstance(outerCanvasInstance: OuterCanvasFragment, paramWidth: Int, paramHeight: Int): CanvasFragment {
            val fragment = CanvasFragment(outerCanvasInstance)
            fragment.setParams(paramWidth, paramHeight)

            return fragment
        }
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