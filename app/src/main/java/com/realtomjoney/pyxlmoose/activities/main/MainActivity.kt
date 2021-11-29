package com.realtomjoney.pyxlmoose.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import com.realtomjoney.pyxlmoose.fragments.NewCanvasFragment
import com.realtomjoney.pyxlmoose.listeners.NewCanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.RecentCreationsListener
import com.realtomjoney.pyxlmoose.models.PixelArt

class MainActivity : AppCompatActivity(), RecentCreationsListener, NewCanvasFragmentListener {
    lateinit var binding: ActivityMainBinding
    var hasNavigatedBack = false

    lateinit var newCanvasFragmentInstance: NewCanvasFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setOnClickListeners()
        setTitle()
    }

    private fun setTitle() {
        title = "Home"
    }

    private fun setOnClickListeners() = extendedSetOnClickListeners()

    override fun onResume() {
        binding.recentCreationsRecyclerView.visibility = View.VISIBLE
        extendedOnResume()
        super.onResume()
    }

    private fun setBindings() = extendedSetBindings()

    override fun onCreationTapped(param: PixelArt) = extendedOnCreationTapped(param)

    fun refreshAdapter() = extendedRefreshAdapter()

    override fun onCreationLongTapped(param: PixelArt) = extendedOnCreationLongTapped(param)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textFieldTwo: TextInputEditText) = extendedOnDoneButtonPressed(spanCount, textField, textFieldTwo)
}

