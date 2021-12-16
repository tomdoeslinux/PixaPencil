package com.realtomjoney.pyxlmoose.fragments

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.canvas.canvasFragmentInstance
import com.realtomjoney.pyxlmoose.activities.canvas.index
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.database.AppData
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import com.realtomjoney.pyxlmoose.converters.BitmapConverter


class CanvasFragment(private val spanCount: Int) : Fragment() {
    private var _binding: FragmentCanvasBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: CanvasFragmentListener

    lateinit var myCanvasViewInstance: MyCanvasView

    companion object {
        fun newInstance(spanCount: Int) = CanvasFragment(spanCount)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CanvasFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCanvasBinding.inflate(inflater, container, false)

        myCanvasViewInstance = MyCanvasView(requireContext(), spanCount.toDouble())

        binding.fragmentCanvasRootLayout.addView(myCanvasViewInstance)

        if (index != -1) canvasFragmentInstance.myCanvasViewInstance.loadData(this, index!!)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}