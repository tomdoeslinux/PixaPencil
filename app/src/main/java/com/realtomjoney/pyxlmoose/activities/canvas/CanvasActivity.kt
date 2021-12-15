package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.fragments.ColorPickerFragment
import com.realtomjoney.pyxlmoose.listeners.*

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColorPickerListener, ColorPickerFragmentListener, FindAndReplaceFragmentListener {
    var previousView: View? = null

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    fun initColorPickerFragmentInstance() = ColorPickerFragment.newInstance(getSelectedColor())

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColor() = if (isPrimaryColorSelected) primaryColor else secondaryColor

    fun setColors() {
        setPrimaryPixelColor(Color.BLACK)
        setSecondaryPixelColor(Color.BLUE)
    }

    fun setPixelColor(it: Int) = if (isPrimaryColorSelected) setPrimaryPixelColor(it) else setSecondaryPixelColor(it)

    private fun setPrimaryPixelColor(color: Int) {
        primaryColor = color
        binding.activityCanvasColorPrimaryView.setBackgroundColor(color)
    }

    private fun setSecondaryPixelColor(color: Int) {
        secondaryColor = color
        binding.activityCanvasColorSecondaryView.setBackgroundColor(color)
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    fun setOnClickListeners() = extendedSetOnClickListeners()

    fun setUpRecyclerView() = extendedSetUpRecyclerView()

    fun setUpFragment() = extendedSetUpFragment()

    fun setBindings() = extendedSetBindings()

    override fun onPixelTapped(instance: MyCanvasView, rectTapped: RectF) = extendedOnPixelTapped(instance, rectTapped)

    override fun onActionUp() {
        canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())
    }

    fun getGradientDrawable() = extendedGetGradientDrawable()

    fun updateColorSelectedIndicator(it: View) = extendedUpdateColorSelectedIndicator(it)

    override fun onColorTapped(color: Int, it: View) = extendedOnColorTapped(color, it)

    override fun onDoneButtonPressed(selectedColor: Int) = extendedOnDoneButtonPressed(selectedColor)

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() = extendedOnBackPressed()
}

