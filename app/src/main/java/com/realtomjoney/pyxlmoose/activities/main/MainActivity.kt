package com.realtomjoney.pyxlmoose.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import com.realtomjoney.pyxlmoose.fragments.NewCanvasFragment
import com.realtomjoney.pyxlmoose.listeners.NewCanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.RecentCreationsListener
import com.realtomjoney.pyxlmoose.models.PixelArts

class MainActivity : AppCompatActivity(), RecentCreationsListener, NewCanvasFragmentListener {
    lateinit var binding: ActivityMainBinding

    val context = this

    lateinit var newCanvasFragmentInstance: NewCanvasFragment

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setOnClickListeners()
        setTitle()

        AppData.db = PixelArtDatabase.getDatabase(this)
    }

    private fun setTitle() {
        title = "Home"
    }

    private fun setOnClickListeners() = extendedSetOnClickListeners()

    override fun onResume() {
        extendedOnResume()
        super.onResume()
    }

    private fun setBindings() = extendedSetBindings()

    override fun onCreationTapped(param: PixelArts) = extendedOnCreationTapped(param)

    override fun onCreationLongTapped(param: PixelArts) = extendedOnCreationLongTapped(param)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textFieldTwo: TextInputEditText) = extendedOnDoneButtonPressed(spanCount, textFieldTwo)
}

