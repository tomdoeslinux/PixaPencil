package com.realtomjoney.pyxlmoose.activities.canvas

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.fragments.ColorPickerFragment
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColorPickerFragmentListener
import com.realtomjoney.pyxlmoose.listeners.ColorPickerListener
import com.realtomjoney.pyxlmoose.listeners.FindAndReplaceFragmentListener
import com.realtomjoney.pyxlmoose.models.Pixel

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColorPickerListener, ColorPickerFragmentListener, FindAndReplaceFragmentListener {

    var previousView: View? = null

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    fun initColorPickerFragmentInstance() = ColorPickerFragment.newInstance(getSelectedColour())

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColour() = if (isPrimaryColourSelected) primaryColour else secondaryColour

    fun evaluate(event: MotionEvent) = extendedEvaluate(event)

    override fun onTouchEvent(event: MotionEvent?) = if (extendedOnTouchEvent(event)) extendedOnTouchEvent(event) else super.onTouchEvent(event)

    fun setColours() {
        setPrimaryPixelColour(Color.BLACK)
        setSecondaryPixelColour(Color.BLUE)
    }

    fun setPixelColour(it: Int) = if (isPrimaryColourSelected) setPrimaryPixelColour(it) else setSecondaryPixelColour(it)

    private fun setPrimaryPixelColour(colour: Int) {
        primaryColour = colour
        binding.activityCanvasColorPrimaryView.setBackgroundColor(colour)
    }

    private fun setSecondaryPixelColour(colour: Int) {
        secondaryColour = colour
        binding.activityCanvasColorSecondaryView.setBackgroundColor(colour)
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
        if (index != -1) {
            AppData.db.pixelArtCreationsDao().updatePixelArtCreationBitmap(
                BitmapConverter.bitmapToString(binding.fragmentHost.drawToBitmap()),
                currentPixelArtsObj.objId
            )

            AppData.db.pixelArtCreationsDao().updatePixelArtCreationPixelData(
                JsonConverter.convertListOfViewToJsonString(dataAsListOfPixels()),
                currentPixelArtsObj.objId
            )
        }
        if (currentFragmentInstance != null) this.navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
        startActivity(Intent(context, MainActivity::class.java))
    }

    fun dataAsListOfPixels(): List<Pixel> {
        val dataAsListOfPixels = mutableListOf<Pixel>()
        for (view in data) {
            if (view.background != null) dataAsListOfPixels.add(Pixel((view.background as ColorDrawable).color)) else dataAsListOfPixels.add(Pixel(null))
        }
        return dataAsListOfPixels.toList()
    }
}

