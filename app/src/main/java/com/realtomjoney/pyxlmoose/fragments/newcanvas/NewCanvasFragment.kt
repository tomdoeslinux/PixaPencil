package com.realtomjoney.pyxlmoose.fragments.newcanvas

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
    private var root: View? = null

    private fun setOnClickListeners() {
        binding.fragmentNewCanvasDoneButton.setOnClickListener {
            try {
                caller.onDoneButtonPressed(
                    Integer.parseInt(binding.fragmentNewCanvasSpanCountTextInputEditText.text.toString()),
                    binding.fragmentNewCanvasSpanCountTextInputEditText,
                    binding.fragmentNewCanvasProjectTitleTextInputEditText
                )
            } catch (ex: Exception) {
                (root as ConstraintLayout).showSnackbar(ex.message.toString(), SnackbarDuration.DEFAULT)
            }
        }
    }

    companion object {
        fun newInstance() = NewCanvasFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewCanvasFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewCanvasBinding.inflate(inflater, container, false)

        root = binding.fragmentNewCanvasRootLayout
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}