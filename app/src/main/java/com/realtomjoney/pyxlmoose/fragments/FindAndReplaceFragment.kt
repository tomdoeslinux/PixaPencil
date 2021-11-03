package com.realtomjoney.pyxlmoose.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.activities.canvas.extendedSetUpRecyclerView
import com.realtomjoney.pyxlmoose.adapters.ColourPickerAdapter
import com.realtomjoney.pyxlmoose.database.ColourDatabase
import com.realtomjoney.pyxlmoose.databinding.FragmentFindAndReplaceBinding
import com.realtomjoney.pyxlmoose.listeners.ColourPickerListener

class FindAndReplaceFragment(private val canvasColors: List<Int>) : Fragment() {

    private var _binding: FragmentFindAndReplaceBinding? = null

    private val binding get() = _binding!!

    internal class FragmentFindAndReplaceCanvasColorsCaller(val binding: FragmentFindAndReplaceBinding) : ColourPickerListener {
        override fun onColourTapped(colour: Int, it: View) {
            binding.fragmentFindAndReplaceColorToFind.setBackgroundColor(colour)
        }
    }

    internal class FragmentFindAndReplaceAvailableColorsRecyclerView(val binding: FragmentFindAndReplaceBinding) : ColourPickerListener {
        override fun onColourTapped(colour: Int, it: View) {
            binding.fragmentFindAndReplaceColorToReplace.setBackgroundColor(colour)
        }
    }

    companion object {
        fun newInstance(canvasColors: List<Int>) = FindAndReplaceFragment(canvasColors)
    }

    private fun setUpCanvasColorsRecyclerView() {
        val layoutManager = LinearLayoutManager(this.activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager = layoutManager
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColourPickerAdapter(canvasColors, FragmentFindAndReplaceCanvasColorsCaller(binding))
    }

    private fun setUpAvailableColorsRecyclerView() {
        val layoutManager = LinearLayoutManager(this.activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager = layoutManager
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColourPickerAdapter(ColourDatabase.toList(), FragmentFindAndReplaceAvailableColorsRecyclerView(binding))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindAndReplaceBinding.inflate(inflater, container, false)
        setUpCanvasColorsRecyclerView()
        setUpAvailableColorsRecyclerView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}