package com.realtomjoney.pyxlmoose.fragments

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.ColorPickerAdapter
import com.realtomjoney.pyxlmoose.database.ColorDatabase
import com.realtomjoney.pyxlmoose.databinding.FragmentFindAndReplaceBinding
import com.realtomjoney.pyxlmoose.listeners.ColorPickerListener
import com.realtomjoney.pyxlmoose.listeners.FindAndReplaceFragmentListener

class FindAndReplaceFragment(private val canvasColors: List<Int>) : Fragment() {

    private var _binding: FragmentFindAndReplaceBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: FindAndReplaceFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
    }

    internal class FragmentFindAndReplaceCanvasColorsCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(color: Int, it: View) {
            binding.fragmentFindAndReplaceColorToFind.setBackgroundColor(color)
        }
    }

    internal class FragmentFindAndReplaceAvailableColorsRecyclerView(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(color: Int, it: View) {
            binding.fragmentFindAndReplaceColorToReplace.setBackgroundColor(color)
        }
    }

    companion object {
        fun newInstance(canvasColors: List<Int>) = FindAndReplaceFragment(canvasColors)
    }

    private fun setUpCanvasColorsRecyclerView() {
        val layoutManager = LinearLayoutManager(this.activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager = layoutManager
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(canvasColors, FragmentFindAndReplaceCanvasColorsCaller(binding))
    }

    private fun setUpAvailableColorsRecyclerView() {
        val layoutManager = LinearLayoutManager(this.activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager = layoutManager
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(ColorDatabase.toList(), FragmentFindAndReplaceAvailableColorsRecyclerView(binding))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindAndReplaceBinding.inflate(inflater, container, false)
        setUpCanvasColorsRecyclerView()
        setUpAvailableColorsRecyclerView()

        binding.fragmentFindAndReplaceDoneButton.setOnClickListener {
            caller.onDoneButtonPressed((binding.fragmentFindAndReplaceColorToFind.background as ColorDrawable).color,
                (binding.fragmentFindAndReplaceColorToReplace.background as ColorDrawable).color)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}