package com.realtomjoney.pyxlmoose.activity.canvas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.realtomjoney.pyxlmoose.*

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColourPickerListener,
    ColorPickerFragmentListener {
    var previousView: View? = null

//    private val transaction = supportFragmentManager
//        .beginTransaction()
//    private val instance = ColorPickerFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendedOnCreate()
//        binding.colorPickerFragmentHost.bringToFront()

//        transaction.replace(R.id.colorPickerFragmentHost, instance).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColour() = if (isPrimaryColourSelected) primaryColour else secondaryColour


    fun setColours() {
        setPrimaryPixelColour(Color.BLACK)
        setSecondaryPixelColour(Color.MAGENTA)
    }

    fun setPixelColour(it: Int) = if (isPrimaryColourSelected) setPrimaryPixelColour(it) else setSecondaryPixelColour(it)

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    private fun setPrimaryPixelColour(colour: Int) {
        primaryColour = colour
        binding.colourPrimarySelected.setBackgroundColor(colour)
    }

    private fun setSecondaryPixelColour(colour: Int) {
        secondaryColour = colour
        binding.colourSecondarySelected.setBackgroundColor(colour)
    }

    fun setOnClickListeners() = extendedSetOnClickListeners()

    fun setUpRecyclerView() = extendedSetUpRecyclerView()

    fun setUpFragment() = extendedSetUpFragment()

    fun setBindings() = extendedSetBindings()

    override fun initPixels() = extendedInitPixels()

    override fun onPixelTapped(pixel: View) = extendedOnPixelTapped(pixel)

    fun getGradientDrawable() = extendedGetGradientDrawable()

    fun updateColourSelectedIndicator(it: View) = extendedUpdateColourSelectedIndicator(it)

    override fun onColourTapped(colour: Int, it: View) = extendedOnColourTapped(colour, it)

    override fun onDoneButtonPressed() {
//        with(supportFragmentManager.beginTransaction()) {
//            remove(instance)
//            commit()
//            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//        }
    }
}

