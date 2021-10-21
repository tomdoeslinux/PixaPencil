package com.realtomjoney.pyxlmoose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CanvasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_canvas, container, false)
    }

    companion object {
        fun newInstance(): CanvasFragment {
            return CanvasFragment()
        }
    }
}