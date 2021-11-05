package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentTransaction
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.fragments.ColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColourPickerListener
import com.realtomjoney.pyxlmoose.listeners.FindAndReplaceFragmentListener
import kotlin.math.pow

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

    private fun evaluate(event: MotionEvent) {
        var count = 0
        for (px in data) {
            val originalPos = IntArray(2)
            px.getLocationInWindow(originalPos)

            val sensitivity = ((400) * (0.9.pow(spanCount)) + 8.9)

            if (originalPos[0] - event.x.toDouble() in -sensitivity..sensitivity && originalPos[1] - event.y.toDouble() in -sensitivity..sensitivity) {
                onPixelTapped(px)
                count++

                if (count == 1) return
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

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
        with(supportFragmentManager.beginTransaction()) {
        remove(findAndReplaceFragmentInstance)
            commit()
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }

        data.forEach {
            if (it.background != null) {
                if ((it.background as ColorDrawable).color == colorToFind) {
                    if (colorToReplace != null) {
                        it.setBackgroundColor(colorToReplace)
                    }
                }
            }
        }

        canvasFragmentInstance.modifyPixels(data)

        title = "PyxlMoose"
    }
}

