package com.therealbluepandabear.pixapencil.fragments.outercanvas

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.customviews.transparentbackgroundview.TransparentBackgroundView
import com.therealbluepandabear.pixapencil.databinding.FragmentOuterCanvasBinding
import com.therealbluepandabear.pixapencil.enums.RotationValue
import com.therealbluepandabear.pixapencil.extensions.rotate
import com.therealbluepandabear.pixapencil.fragments.canvas.CanvasFragment
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

lateinit var canvasFragment: CanvasFragment

@SuppressLint("ClickableViewAccessibility")
class OuterCanvasFragment : Fragment() {
    private var _binding: FragmentOuterCanvasBinding? = null

    private val binding get(): FragmentOuterCanvasBinding {
        return _binding!!
    }

    lateinit var cardViewParent: CardView
    lateinit var fragmentHost: FrameLayout
    lateinit var transparentBackgroundView: TransparentBackgroundView

    private var paramWidth: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    private var paramHeight: Int = IntConstants.DEFAULT_CANVAS_WIDTH_HEIGHT
    private var paramProjectTitle: String? = null
    private var paramIndex: Int = -1

    private var originalX: Float? = null
    private var originalY: Float? = null

    private var dX = 0f
    private var dY = 0f

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

        binding.fragmentOuterCanvasCanvasFragmentHost.post {
            setOriginalCoordinates()
        }
    }

    private fun setOriginalCoordinates() {
        if (originalX == null) {
            originalX = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.x
        }

        if (originalY == null) {
            originalY = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.y
        }
    }

    fun removeOnTouchListener() {
        binding.fragmentOuterCanvasMoveView.setOnTouchListener { _, _ -> false }
    }

    fun setOnTouchListener() {
        binding.fragmentOuterCanvasMoveView.setOnTouchListener(onMoveViewTouchListener())
    }

    fun resetPosition() {
        binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.x = originalX ?: 0f
        binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.y = originalY ?: 0f
        binding.root.invalidate()
    }

    private fun onMoveViewTouchListener(): View.OnTouchListener {
        return View.OnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.x - event.rawX
                    dY = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.y - event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.y = event.rawY + dY
                    binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent.x = event.rawX + dX
                }
            }

            binding.root.invalidate()
            true
        }
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

    fun drawFragmentHostToBitmap(): Bitmap {
        return fragmentHost
            .drawToBitmap()
            .rotate(getCurrentRotation().toInt())
    }

    fun drawPixelGridViewBitmap(): Bitmap {
        return pixelGridViewInstance.pixelGridViewBitmap
            .rotate(getCurrentRotation().toInt())
    }

    fun drawTransparentBackgroundViewBitmap(): Bitmap {
        return transparentBackgroundView.transparentBackgroundViewBitmap
            .rotate(getCurrentRotation().toInt())
    }

    companion object {
        fun newInstance(paramWidth: Int, paramHeight: Int, paramProjectTitle: String?, paramIndex: Int): OuterCanvasFragment {
            val fragment = OuterCanvasFragment()
            fragment.setParams(paramWidth, paramHeight, paramProjectTitle, paramIndex)

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOuterCanvasBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}