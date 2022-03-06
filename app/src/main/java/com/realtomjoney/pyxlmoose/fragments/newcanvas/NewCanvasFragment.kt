package com.realtomjoney.pyxlmoose.fragments.newcanvas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.FragmentNewCanvasBinding
import com.realtomjoney.pyxlmoose.listeners.NewCanvasFragmentListener

class NewCanvasFragment : Fragment() {
    var root: View? = null

    private fun instantiateRoot() {
        root = binding.fragmentNewCanvasRootLayout
    }

    companion object {
        fun newInstance() = NewCanvasFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewCanvasFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentNewCanvasBinding.inflate(inflater, container, false)

        instantiateRoot()
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}