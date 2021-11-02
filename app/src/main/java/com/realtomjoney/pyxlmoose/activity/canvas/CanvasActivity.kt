package com.realtomjoney.pyxlmoose.activity.canvas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentTransaction
import com.realtomjoney.pyxlmoose.*
import kotlin.math.pow

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColourPickerListener,
    ColorPickerFragmentListener {
    var previousView: View? = null

    var instance = initColorPickerFragmentInstance()
    var instance2 = CanvasFragment.newInstance(spanCount, true, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    fun initColorPickerFragmentInstance() = ColorPickerFragment.newInstance(getSelectedColour())

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColour() = if (isPrimaryColourSelected) primaryColour else secondaryColour

    override fun onResume() {
        binding.doneButton.animate().scaleX(1f).scaleY(1f).setDuration(300).withEndAction {
            binding.doneButton.visibility = View.VISIBLE
        }

        super.onResume()
    }

    private fun evaluate(event: MotionEvent) {
        var count = 0
        for (px in data) {
            val originalPos = IntArray(2)
            px.getLocationInWindow(originalPos)

            val sensitivity = ((400) * (0.9.pow(spanCount)) + 8.9)

            if (originalPos[0] - event.x.toDouble() in -sensitivity..sensitivity && originalPos[1] - event.y.toDouble() in -sensitivity..sensitivity) {
                onPixelTapped(px)
                count++

                if (count == 1)
                    return
            }
        }
    }

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
        currentBackground = null
        hasSetBackgroundYet = false
        wantsToChangeBackground = false
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

    override fun onDoneButtonPressed(selectedColor: Int) {
        with(supportFragmentManager.beginTransaction()) {
            remove(instance)
            commit()
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }
        setPixelColour(selectedColor)

        binding.colorPickerFragmentHost.visibility = View.INVISIBLE
        binding.doneButton.scaleX = 1f
        binding.doneButton.scaleY = 1f
        binding.doneButton.visibility = View.VISIBLE

        title = "PyxlMoose"
    }
}

