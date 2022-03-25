package com.therealbluepandabear.pyxlmoose.fragments.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding
import com.therealbluepandabear.pyxlmoose.customviews.pixelgridview.PixelGridView

class CanvasFragment : Fragment() {
    lateinit var myCanvasViewInstance: PixelGridView

    var bitmap: Bitmap? = null

    private var paramWidth: Int = 5
    private var paramHeight: Int = 5

    fun setParams(paramWidth: Int, paramHeight: Int) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
    }

    private fun setupCanvas() {
        myCanvasViewInstance = PixelGridView(requireContext(), paramWidth, paramHeight)
        binding.fragmentCanvasRootLayout.addView(myCanvasViewInstance)
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int): CanvasFragment {
            val fragment = CanvasFragment()
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