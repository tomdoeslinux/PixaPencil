package com.realtomjoney.pyxlmoose.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.realtomjoney.pyxlmoose.databinding.FragmentNewCanvasBinding
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.listeners.NewCanvasFragmentListener

class NewCanvasFragment : Fragment() {

    private var _binding: FragmentNewCanvasBinding? = null

    private val binding get() = _binding!!

    var root: View? = null

    private lateinit var caller: NewCanvasFragmentListener

    companion object {
        fun newInstance() = NewCanvasFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewCanvasFragmentListener) caller = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewCanvasBinding.inflate(inflater, container, false)

        root = binding.fragmentNewCanvasRootLayout

        binding.fragmentNewCanvasDoneButton.setOnClickListener {
            caller.onDoneButtonPressed(Integer.parseInt(binding.textField.text.toString()), binding.textField)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}