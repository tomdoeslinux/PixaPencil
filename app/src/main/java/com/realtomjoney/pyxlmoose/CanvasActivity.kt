package com.realtomjoney.pyxlmoose

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
import android.widget.EditText
import android.widget.Toast
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

var primaryColour: Int = Color.BLACK
var secondaryColour: Int = Color.MAGENTA
var spanCount = 5
const val TOAST_MESSAGE = "Please name your project."
var isPrimaryColourSelected = true
var isMirrorMode = false
var pixelGridOn = true

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColourPickerListener {
    private lateinit var binding: ActivityCanvasBinding
    private var data = listOf<View>()
    private var index: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spanCount = intent.getIntExtra("SPAN_COUNT", spanCount)
        index = intent.getIntExtra("INDEX", -1)

        if (index != -1) data = BitmapDatabase.toList()[index!!].pixelData

        setBindings()
        setUpFragment()
        setUpRecyclerView()
        setOnClickListeners()
        setColours()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val zoom = 0.3f
        when (item.itemId) {
            R.id.zoom_out -> {
                binding.fragmentHost.scaleX -= zoom; binding.fragmentHost.scaleY -= zoom
            }
            R.id.zoom_in -> {
                binding.fragmentHost.scaleX += zoom; binding.fragmentHost.scaleY += zoom
            }
            R.id.pixel_grid_on_off -> {
                if (pixelGridOn) {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, false, data)).commit()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_on_24)
                    item.title = "Turn Grid On"

                    pixelGridOn = false

                } else if (!pixelGridOn) {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentHost, CanvasFragment.newInstance(spanCount, true, data)).commit()
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_off_24)
                    item.title = "Turn Grid Off"

                    pixelGridOn = true
                }

            }

        }; return true
    }

    private fun getSelectedColour(): Int {
        return if (isPrimaryColourSelected) primaryColour else secondaryColour
    }

    private fun setColours() {
        setPrimaryPixelColour(Color.BLACK)
        setSecondaryPixelColour(Color.MAGENTA)
    }

    private fun setPixelColour(it: Int) = if (isPrimaryColourSelected) setPrimaryPixelColour(it) else setSecondaryPixelColour(it)

    override fun onPause() {
        super.onPause()
        BitmapDatabase.addBitmap(SavedPixelArt(binding.fragmentHost.drawToBitmap(), "Unnamed project", data))
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
                BitmapDatabase.addBitmap(SavedPixelArt(binding.fragmentHost.drawToBitmap(), binding.titleTextView.text.toString(), data))
                isMirrorMode = false
                super.onBackPressed()
            } else {
                Toast.makeText(this, TOAST_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }

        binding.chooseColourFromHexButton.setOnClickListener { getHexDialogBuilder().show() }

        binding.chooseColourFromRGBButton.setOnClickListener { getRGBDialogBuilder().show() }

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

    private fun getHexDialogBuilder(): MaterialAlertDialogBuilder {
        val hexadecimalDialogInput = EditText(this)
        hexadecimalDialogInput.hint = "Hex value"

        return MaterialAlertDialogBuilder(this)
            .setTitle("Hexadecimal")
            .setMessage("Please input a valid hexadecimal value:")
            .setView(hexadecimalDialogInput)
            .setPositiveButton("Done") { _, _ ->
                try {
                    setPixelColour(Color.parseColor(hexadecimalDialogInput.text.toString()))
                } catch (exception: Exception) {
                    Snackbar.make(binding.rootLayout, exception.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("Back") { _, _ -> }
    }

    private fun getRGBDialogBuilder(): MaterialAlertDialogBuilder {
        val dialogueLayout = LinearLayout(this)
        dialogueLayout.orientation = LinearLayout.VERTICAL

        val editTexts = mutableListOf<EditText>()

        listOf("R", "G", "B").forEach {
            val editText = EditText(this)
            editText.hint = it
            dialogueLayout.addView(editText)
            editTexts.add(editText)
        }

        return MaterialAlertDialogBuilder(this)
            .setTitle("RGB")
            .setMessage("Please input a valid RGB value:")
            .setView(dialogueLayout)
            .setPositiveButton("Done") { _, _ ->
                setPixelColour(Color.argb(
                    100,
                    editTexts[0].text.toString().toInt(),
                    editTexts[1].text.toString().toInt(),
                    editTexts[2].text.toString().toInt(),
                ))
            }
            .setNegativeButton("Back") { _, _ -> }
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
            for (i in 1..spanCount * spanCount) { list.add(View(this)) }
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
}

