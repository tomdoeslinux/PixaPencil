package com.realtomjoney.pyxlmoose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentCanvasBinding

class CanvasFragment : Fragment() {
    private var _binding: FragmentCanvasBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCanvasBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): CanvasFragment {
            return CanvasFragment()
        }
    }
}