package com.realtomjoney.pyxlmoose.activity.canvas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.*
import android.widget.Toast
import androidx.core.graphics.ColorUtils
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
    private var index: Int? = null

//    private val transaction = supportFragmentManager
//        .beginTransaction()
//    private val instance = ColorPickerFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spanCount = intent.getIntExtra(StringValues.SPAN_COUNT_EXTRA, spanCount)
        index = intent.getIntExtra(StringValues.INDEX_EXTRA, -1)

        if (index != -1) data = PixelArtDatabase.toList()[index!!].pixelData

        setBindings()
        setUpFragment()
        setUpRecyclerView()
        setOnClickListeners()
        setColours()

//        binding.colorPickerFragmentHost.bringToFront()

//        transaction.replace(R.id.colorPickerFragmentHost, instance).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    private fun getSelectedColour(): Int {
        return if (isPrimaryColourSelected) primaryColour else secondaryColour
    }

    private fun setColours() {
        setPrimaryPixelColour(Color.BLACK)
        setSecondaryPixelColour(Color.MAGENTA)
    }

    private fun setPixelColour(it: Int) = if (isPrimaryColourSelected) setPrimaryPixelColour(it) else setSecondaryPixelColour(it)

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

    private fun setOnClickListeners() {
        binding.doneButton.setOnClickListener {
            if (binding.titleTextView.text.toString().isNotBlank()) {
                PixelArtDatabase.addItem(PixelArt(binding.fragmentHost.drawToBitmap(), binding.titleTextView.text.toString(), data, false))
                isMirrorMode = false
                hasSaved = true
                super.onBackPressed()
            } else {
                Toast.makeText(this, StringValues.MESSAGE_NAME_PROJECT, Toast.LENGTH_SHORT).show()
            }
        }

        binding.colourSecondarySelected.setOnClickListener {
            isPrimaryColourSelected = false
            setPixelColour((binding.colourSecondarySelected.background as ColorDrawable).color)
        }

        binding.colourPrimarySelected.setOnClickListener {
            isPrimaryColourSelected = true
            setPixelColour((binding.colourPrimarySelected.background as ColorDrawable).color)
        }

        binding.mirrorButton.setOnClickListener { isMirrorMode = !isMirrorMode }

        binding.darkenButton.setOnClickListener {
            if (isPrimaryColourSelected) setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.BLACK, 0.2f))
            else setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.BLACK, 0.2f))
        }

        binding.lightenButton.setOnClickListener {
            if (isPrimaryColourSelected) setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.WHITE, 0.2f))
            else setPixelColour(ColorUtils.blendARGB(getSelectedColour(), Color.WHITE, 0.2f))
        }
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.colourPickerRecyclerView.layoutManager = layoutManager
        binding.colourPickerRecyclerView.adapter = ColourPickerAdapter(ColourDatabase.toList(), this)
    }

    private fun setUpFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, true, null)).commit()
    }

    private fun setBindings() {
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initPixels(): List<View> {
        return if (index == -1) {
            val list = mutableListOf<View>()
            for (i in 1..spanCount * spanCount) {
                list.add(View(this))
            }
            data = list
            list.toList()
        } else {
            data.toList()
        }
    }

    override fun onPixelTapped(pixel: View) {
        if (isMirrorMode) {
            data[((data.indexOf(pixel)) - ((data.indexOf(pixel)).mod(spanCount))) + (spanCount -  ((data.indexOf(pixel)).mod(spanCount))) - 1].setBackgroundColor(
                getSelectedColour()
            ) // Credits to PapaBread for this masterpiece of a solution
        }
        pixel.setBackgroundColor(getSelectedColour())
    }

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

