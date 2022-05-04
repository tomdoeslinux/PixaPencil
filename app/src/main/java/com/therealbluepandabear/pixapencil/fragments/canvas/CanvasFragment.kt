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

class CanvasFragment : Fragment() {
    var bitmap: Bitmap? = null

    private var paramWidth: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramHeight: Int = IntConstants.DefaultCanvasWidthHeight
    private var outerCanvasInstance: OuterCanvasFragment? = null

    fun setParams(paramWidth: Int, paramHeight: Int, outerCanvasInstance: OuterCanvasFragment) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
        this.outerCanvasInstance = outerCanvasInstance
    }

    private fun setupCanvas() {
        if (outerCanvasInstance != null) {
            pixelGridViewInstance = PixelGridView(requireContext(), paramWidth, paramHeight, outerCanvasInstance!!)
            binding.fragmentCanvasRootLayout.addView(pixelGridViewInstance)
        }
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int, outerCanvasInstance: OuterCanvasFragment): CanvasFragment {
            val fragment = CanvasFragment()
            fragment.setParams(paramWidth, paramHeight, outerCanvasInstance)

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