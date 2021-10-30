package com.realtomjoney.pyxlmoose.activity.canvas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.*
import com.realtomjoney.pyxlmoose.*

var primaryColour: Int = Color.BLACK
var secondaryColour: Int = Color.MAGENTA
var spanCount = 5
var isPrimaryColourSelected = true
var isMirrorMode = false
var pixelGridOn = true
var hasSaved = false

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColourPickerListener,
    ColorPickerFragmentListener {
    lateinit var binding: ActivityCanvasBinding
    var data = listOf<View>()
    var index: Int? = null

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

    fun setUpFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, true, null)).commit()
    }

    fun setBindings() {
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initPixels() = extendedInitPixels()

    override fun onPixelTapped(pixel: View) = extendedOnPixelTapped(pixel)

    private var isSelected = false
    private var previousView: View? = null
    private var background: Drawable? = null

    private fun getGradientDrawable(): GradientDrawable {
        val gd = GradientDrawable()
        gd.setColor(Color.RED)
        gd.cornerRadius = 10f
        gd.setStroke(2, Color.BLACK)
        return gd
    }

    private fun updateColourSelectedIndicator(it: View) {
        previousView?.background = background

        previousView = it
        background = it.background

        it.background = getGradientDrawable()
    }

    override fun onColourTapped(colour: Int, it: View) {
        setPixelColour(colour)

        isSelected = if (!isSelected) {
            updateColourSelectedIndicator(it)
            true
        } else {
            updateColourSelectedIndicator(it)
            false
        }
    }

    override fun onDoneButtonPressed() {
//        with(supportFragmentManager.beginTransaction()) {
//            remove(instance)
//            commit()
//            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//        }
    }
}

