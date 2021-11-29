package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.fragments.ColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColourPickerListener
import com.realtomjoney.pyxlmoose.listeners.FindAndReplaceFragmentListener

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColourPickerListener,
    ColorPickerFragmentListener, FindAndReplaceFragmentListener {
    var previousView: View? = null

    lateinit var colorPickerFragmentInstance: ColorPickerFragment
    lateinit var canvasFragmentInstance: CanvasFragment
    lateinit var findAndReplaceFragmentInstance: FindAndReplaceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    fun initColorPickerFragmentInstance() = ColorPickerFragment.newInstance(getSelectedColour())

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColour() = if (isPrimaryColourSelected) primaryColour else secondaryColour

    override fun onResume() {
        binding.doneButton.animate().scaleX(1f).scaleY(1f).setDuration(300).withEndAction { binding.doneButton.visibility = View.VISIBLE }
        super.onResume()
    }

    private fun evaluate(event: MotionEvent) = extendedEvaluate(event)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event!!.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                evaluate(event)
                true
            }
            else -> super.onTouchEvent(event)
        }
    }


    fun setColours() {
        setPrimaryPixelColour(Color.BLACK)
        setSecondaryPixelColour(Color.BLUE)
    }

    fun setPixelColour(it: Int) = if (isPrimaryColourSelected) setPrimaryPixelColour(it) else setSecondaryPixelColour(it)

    private fun setPrimaryPixelColour(colour: Int) {
        primaryColour = colour
        binding.colourPrimarySelected.setBackgroundColor(colour)
    }

    private fun setSecondaryPixelColour(colour: Int) {
        secondaryColour = colour
        binding.colourSecondarySelected.setBackgroundColor(colour)
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
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

    override fun onDoneButtonPressed(selectedColor: Int) = extendedOnDoneButtonPressed(selectedColor)

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() {
        this.navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
    }
}

