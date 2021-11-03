package com.realtomjoney.pyxlmoose.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.pyxlmoose.databinding.FragmentFindAndReplaceBinding

class FindAndReplaceFragment : Fragment() {

    private var _binding: FragmentFindAndReplaceBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FindAndReplaceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindAndReplaceBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}