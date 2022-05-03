package com.therealbluepandabear.pixapencil.fragments.outercanvas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.customviews.transparentbackgroundview.TransparentBackgroundView
import com.therealbluepandabear.pixapencil.databinding.FragmentOuterCanvasBinding
import com.therealbluepandabear.pixapencil.fragments.canvas.CanvasFragment
import com.therealbluepandabear.pixapencil.utility.IntConstants

lateinit var canvasFragment: CanvasFragment

class OuterCanvasFragment : Fragment() {
    lateinit var cardViewParent: CardView
    lateinit var fragmentHost: FrameLayout
    lateinit var transparentBackgroundView: TransparentBackgroundView

    private var paramWidth: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramHeight: Int = IntConstants.DefaultCanvasWidthHeight

    private fun setParams(paramWidth: Int, paramHeight: Int) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
    }

    private fun instantiateVariables() {
        cardViewParent = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent
        fragmentHost = binding.fragmentOuterCanvasCanvasFragmentHost
        canvasFragment = CanvasFragment.newInstance(this, paramWidth, paramHeight)
    }

    private fun addTransparentBackgroundView() {
        transparentBackgroundView = TransparentBackgroundView(requireContext(), paramWidth, paramHeight)
        binding.defsq2.addView(transparentBackgroundView)
    }

    private fun beginCanvasFragmentTransaction() {
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentOuterCanvas_canvasFragmentHost, canvasFragment).commit()
    }

    private fun setup() {
        instantiateVariables()
        addTransparentBackgroundView()
        beginCanvasFragmentTransaction()
    }

    fun getCurrentRotation(): Float {
        return binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.rotation
    }

    fun rotate(by: Int = IntConstants.DegreesNinety, animate: Boolean = true, clockwise: Boolean = true) {
        val rotationAmount = if (clockwise) {
                (getCurrentRotation() + by)
        } else {
                (getCurrentRotation() - by)
        }

        if (animate) {
            binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent
                .animate()
                .rotation(rotationAmount)
        } else {
            binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.rotation = rotationAmount
        }
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int): OuterCanvasFragment {
            val fragment = OuterCanvasFragment()
            fragment.setParams(paramWidth, paramHeight)

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentOuterCanvasBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}