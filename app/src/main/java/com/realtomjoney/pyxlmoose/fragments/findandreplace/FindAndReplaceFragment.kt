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
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.ColorDatabase
import com.realtomjoney.pyxlmoose.databinding.FragmentFindAndReplaceBinding
import com.realtomjoney.pyxlmoose.listeners.ColorPickerListener
import com.realtomjoney.pyxlmoose.listeners.FindAndReplaceFragmentListener
import com.realtomjoney.pyxlmoose.models.ColorPalette

class FindAndReplaceFragment(private val canvasColors: List<Int>) : Fragment() {
    private fun setupCanvasColorsRecyclerView() {
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager = LinearLayoutManager(this.activity).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(ColorPalette(null, JsonConverter.convertListOfIntToJsonString(canvasColors)), FragmentFindAndReplaceCanvasColorsCaller(binding), false)
    }

    private fun setupAvailableColorsRecyclerView() {
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager = LinearLayoutManager(this.activity).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(ColorPalette(null, JsonConverter.convertListOfIntToJsonString(ColorDatabase.toList())), FragmentFindAndReplaceAvailableColorsRecyclerView(binding), false)
    }

    private fun setOnClickListeners() {
        binding.fragmentFindAndReplaceDoneButton.setOnClickListener {
            caller.onDoneButtonPressed((binding.fragmentFindAndReplaceColorToFind.background as ColorDrawable).color, (binding.fragmentFindAndReplaceColorToReplace.background as ColorDrawable).color)
        }
    }

    internal class FragmentFindAndReplaceCanvasColorsCaller(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) = binding.fragmentFindAndReplaceColorToFind.setBackgroundColor(colorTapped)
        override fun onColorAdded(colorPalette: ColorPalette) {}
    }

    internal class FragmentFindAndReplaceAvailableColorsRecyclerView(val binding: FragmentFindAndReplaceBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int, view: View) = binding.fragmentFindAndReplaceColorToReplace.setBackgroundColor(colorTapped)
        override fun onColorAdded(colorPalette: ColorPalette) {}
    }

    companion object {
        fun newInstance(canvasColors: List<Int>) = FindAndReplaceFragment(canvasColors)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindAndReplaceFragmentListener) caller = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentFindAndReplaceBinding.inflate(inflater, container, false)

        setupCanvasColorsRecyclerView()
        setupAvailableColorsRecyclerView()
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}