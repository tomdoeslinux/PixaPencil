package com.realtomjoney.pyxlmoose.activities.main

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.realtomjoney.pyxlmoose.fragments.newcanvas.NewCanvasFragment
import com.realtomjoney.pyxlmoose.listeners.NewCanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.RecentCreationsListener
import com.realtomjoney.pyxlmoose.models.PixelArt


class MainActivity : AppCompatActivity(), RecentCreationsListener, NewCanvasFragmentListener {
    val context = this

    lateinit var newCanvasFragmentInstance: NewCanvasFragment

    var currentFragmentInstance: Fragment? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setVmPolicy()
        setBindings()
        setOnClickListeners()
        setTitle()
        initializeRoomDatabases()
        requestPermissions()
    }

    override fun onResume() {
        extendedOnResume()
        super.onResume()
    }

    override fun onCreationTapped(creationTapped: PixelArt) = extendedOnCreationTapped(creationTapped)

    override fun onCreationLongTapped(creationTapped: PixelArt) = extendedOnCreationLongTapped(creationTapped)

    override fun onBackPressed() {
        if (currentFragmentInstance != null) extendedOnBackPressed() else super.onBackPressed()
    }

    override fun onDoneButtonPressed(spanCount: Int, textField: TextInputEditText, textFieldTwo: TextInputEditText) = extendedOnDoneButtonPressed(spanCount, textFieldTwo)
}

