package com.realtomjoney.pyxlmoose.fragments.outercanvas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.databinding.FragmentOuterCanvasBinding
import com.realtomjoney.pyxlmoose.fragments.canvas.CanvasFragment

class OuterCanvasFragment(val spanCount: Int, private val isEmpty: Boolean = false) : Fragment() {
    lateinit var canvasFragment: CanvasFragment
    lateinit var cardViewParent: View
    lateinit var fragmentHost: View

    private fun instantiateVariables() {
        cardViewParent = binding.fragmentOuterCanvasCanvasFragmentHostCardViewParent
        fragmentHost = binding.fragmentOuterCanvasCanvasFragmentHost
        canvasFragment = CanvasFragment.newInstance(spanCount, isEmpty)
    }

    private fun showCanvas() {
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentOuterCanvas_canvasFragmentHost, canvasFragment).commit()
    }

    companion object {
        fun newInstance(spanCount: Int, isEmpty: Boolean = false) = OuterCanvasFragment(spanCount, isEmpty)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentOuterCanvasBinding.inflate(inflater, container, false)

        instantiateVariables()
        showCanvas()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}