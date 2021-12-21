package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.fragments.colorpicker.ColorPickerFragment
import com.realtomjoney.pyxlmoose.listeners.*

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColorPickerListener, ColorPickerFragmentListener, FindAndReplaceFragmentListener, ToolsFragmentListener, FiltersFragmentListener {
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

    fun setPixelColor(color: Int) = if (isPrimaryColorSelected) setPrimaryPixelColor(color) else setSecondaryPixelColor(color)

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

    override fun onPixelTapped(canvasInstance: MyCanvasView, rectTapped: RectF) = extendedOnPixelTapped(canvasInstance, rectTapped)

    override fun onActionUp() = extendedOnActionUp()

    fun updateColorSelectedIndicator(it: View) = extendedUpdateColorSelectedIndicator(it)

    override fun onColorTapped(selectedColor: Int, it: View) = extendedOnColorTapped(selectedColor, it)

    override fun onDoneButtonPressed(selectedColor: Int) = extendedOnDoneButtonPressed(selectedColor)

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onToolTapped(toolName: String) = extendedOnToolTapped(toolName)

    override fun onFilterSelected(filterName: String) = extendedOnFilterSelected(filterName)
}
