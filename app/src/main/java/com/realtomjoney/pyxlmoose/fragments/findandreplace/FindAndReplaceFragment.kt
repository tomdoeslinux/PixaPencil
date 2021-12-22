package com.realtomjoney.pyxlmoose.fragments.findandreplace

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

    private fun setUpCanvasColorsRecyclerView() {
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager = LinearLayoutManager(this.activity).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(canvasColors, FragmentFindAndReplaceCanvasColorsCaller(binding))
    }

    private fun setUpAvailableColorsRecyclerView() {
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager = LinearLayoutManager(this.activity).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(ColorDatabase.toList(), FragmentFindAndReplaceAvailableColorsRecyclerView(binding))
    }

    private fun setOnClickListeners() {
        binding.fragmentFindAndReplaceDoneButton.setOnClickListener {
            caller.onDoneButtonPressed((binding.fragmentFindAndReplaceColorToFind.background as ColorDrawable).color, (binding.fragmentFindAndReplaceColorToReplace.background as ColorDrawable).color)
        }
    }

    companion object {
        fun newInstance(canvasColors: List<Int>) = FindAndReplaceFragment(canvasColors)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
    }

    internal class FragmentFindAndReplaceCanvasColorsCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(selectedColor: Int, it: View) = binding.fragmentFindAndReplaceColorToFind.setBackgroundColor(selectedColor)
    }

    internal class FragmentFindAndReplaceAvailableColorsRecyclerView(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(selectedColor: Int, it: View) = binding.fragmentFindAndReplaceColorToReplace.setBackgroundColor(selectedColor)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFindAndReplaceBinding.inflate(inflater, container, false)

        setUpCanvasColorsRecyclerView()
        setUpAvailableColorsRecyclerView()
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}