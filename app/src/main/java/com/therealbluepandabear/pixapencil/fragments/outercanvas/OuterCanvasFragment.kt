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
import com.therealbluepandabear.pixapencil.enums.RotationValue
import com.therealbluepandabear.pixapencil.fragments.canvas.CanvasFragment
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

lateinit var canvasFragment: CanvasFragment

class OuterCanvasFragment : Fragment() {
    lateinit var cardViewParent: CardView
    lateinit var fragmentHost: FrameLayout
    lateinit var transparentBackgroundView: TransparentBackgroundView

    private var paramWidth: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramHeight: Int = IntConstants.DefaultCanvasWidthHeight
    private var paramProjectTitle: String? = null
    private var paramIndex: Int = -1

    private fun setParams(paramWidth: Int, paramHeight: Int, paramProjectTitle: String?, paramIndex: Int) {
        this.paramWidth = paramWidth
        this.paramHeight = paramHeight
        this.paramProjectTitle = paramProjectTitle
        this.paramIndex = paramIndex
    }

    private fun instantiateVariables() {
        cardViewParent = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent
        fragmentHost = binding.fragmentOuterCanvasCanvasFragmentHost
        canvasFragment = CanvasFragment.newInstance(paramWidth, paramHeight, this, paramProjectTitle, paramIndex)
    }

    private fun addTransparentBackgroundView() {
        transparentBackgroundView = TransparentBackgroundView(requireContext(), paramWidth, paramHeight, paramIndex)
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
        return cardViewParent.rotation
    }

    fun rotate(rotationValue: RotationValue, animate: Boolean = false) {
        rotate(rotationValue.degrees, rotationValue.clockwise, animate)
    }

    fun rotate(degrees: Int, clockwise: Boolean = true, animate: Boolean = false) {
        val rotationAmount = if (clockwise) {
            (getCurrentRotation() + degrees)
        } else {
            (getCurrentRotation() - degrees)
        }

        if (animate) {
            cardViewParent
                .animate()
                .rotation(rotationAmount)
        } else {
            cardViewParent.rotation = rotationAmount
        }
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int, paramProjectTitle: String?, paramIndex: Int): OuterCanvasFragment {
            val fragment = OuterCanvasFragment()
            fragment.setParams(paramWidth, paramHeight, paramProjectTitle, paramIndex)

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