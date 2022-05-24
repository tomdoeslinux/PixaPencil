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
import com.therealbluepandabear.pixapencil.listeners.CanvasFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

lateinit var pixelGridViewInstance: PixelGridView

class CanvasFragment : Fragment() {
    var bitmap: Bitmap? = null

    private var paramWidth: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramHeight: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramOuterCanvasInstance: OuterCanvasFragment? = null
    private var paramProjectTitle: String? = null
    private var paramIndex: Int = -1

    var backingBindingProperty: FragmentCanvasBinding? = null

    val binding get(): FragmentCanvasBinding {
        return backingBindingProperty!!
    }

    lateinit var caller: CanvasFragmentListener

    fun setParams(paramWidth: Int, paramHeight: Int, outerCanvasInstance: OuterCanvasFragment, paramProjectTitle: String?, paramIndex: Int) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
        this.paramOuterCanvasInstance = outerCanvasInstance
        this.paramProjectTitle = paramProjectTitle
        this.paramIndex = paramIndex
    }

    private fun setupCanvas() {
        if (paramOuterCanvasInstance != null) {
            pixelGridViewInstance = PixelGridView(requireContext(), paramWidth, paramHeight, paramOuterCanvasInstance!!, paramProjectTitle, paramIndex)
            binding.root.addView(pixelGridViewInstance)
        }
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int, outerCanvasInstance: OuterCanvasFragment, paramProjectTitle: String?, paramIndex: Int): CanvasFragment {
            val fragment = CanvasFragment()
            fragment.setParams(paramWidth, paramHeight, outerCanvasInstance, paramProjectTitle, paramIndex)

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        backingBindingProperty = FragmentCanvasBinding.inflate(inflater, container, false)

        setupCanvas()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        backingBindingProperty = null
    }
}