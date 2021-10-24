package com.realtomjoney.pyxlmoose

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.*
import android.widget.EditText
import android.widget.Toast
import android.widget.LinearLayout

var primaryColour: Int = Color.BLACK
var secondaryColour: Int = Color.MAGENTA
var spanCount = 5
const val TOAST_MESSAGE = "Please name your project."
var isPrimaryColourSelected = true

interface ColourPickerListener {
    fun onColourTapped(colour: Int, it: View)
}


class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColourPickerListener {
    private lateinit var binding: ActivityCanvasBinding

    private var data = listOf<Pixel>()

    private var colourData = ColourDatabase.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spanCount = intent.getIntExtra("SPAN_COUNT", spanCount)

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
        }
        return true
    }

    private fun setColours() {
        setPrimaryPixelColour(Color.BLACK)
        setSecondaryPixelColour(Color.MAGENTA)
    }

    private fun setPixelColour(it: Int) {
        if (isPrimaryColourSelected) setPrimaryPixelColour(it) else setSecondaryPixelColour(it)
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
            if (binding.titleTextView.text.toString() != "") {
                BitmapDatabase.addBitmap(binding.fragmentHost.drawToBitmap())
                super.onBackPressed()
            } else {
                Toast.makeText(this, TOAST_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }

        binding.chooseColourFromHexButton.setOnClickListener {
            getHexDialogBuilder().create().show()
        }

        binding.chooseColourFromRGBButton.setOnClickListener {
            getRGBDialogBuilder().create().show()
        }

        binding.colourSecondarySelected.setOnClickListener {
            isPrimaryColourSelected = false
            setPixelColour((binding.colourSecondarySelected.background as ColorDrawable).color)
        }

        binding.colourPrimarySelected.setOnClickListener {
            isPrimaryColourSelected = true
            setPixelColour((binding.colourPrimarySelected.background as ColorDrawable).color)
        }
    }

    private fun getHexDialogBuilder(): AlertDialog.Builder {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Hexadecimal")
        builder.setMessage("Input a hexadecimal value")

        val input = EditText(this)
        input.hint = "Hex value"

        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            setPixelColour(Color.parseColor(input.text.toString()))
        }

        builder.setNegativeButton("Cancel") { _, _ -> }

        return builder
    }

    private fun getRGBDialogBuilder(): AlertDialog.Builder {
        val hints = listOf("R", "G", "B")

        val editTextNum: Int = hints.size

        val builder = AlertDialog.Builder(this)

        builder.setTitle("RGB")
        builder.setMessage("Input an RGB value")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val editTexts = mutableListOf<EditText>()

        for (i in 0 until editTextNum) {
            val editText = EditText(this)
            editText.hint = hints[i]
            layout.addView(editText)

            editTexts.add(editText)
        }

        builder.setView(layout)

        builder.setPositiveButton("OK") { _, _ ->
            setPixelColour(Color.argb(
                100,
                editTexts[0].text.toString().toInt(),
                editTexts[0].text.toString().toInt(),
                editTexts[0].text.toString().toInt(),
            ))
        }

        builder.setNegativeButton("Cancel") { _, _ -> }

        return builder
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.colourPickerRecyclerView.layoutManager = layoutManager

        binding.colourPickerRecyclerView.adapter = ColourPickerAdapter(colourData, this)
    }

    private fun setUpFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHost, CanvasFragment.newInstance(spanCount)).commit()
    }

    private fun setBindings() {
        binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initPixels(): List<Pixel> {
        val list = mutableListOf<Pixel>()
        for (i in 1..spanCount * spanCount) {
            val px = Pixel(this)
            list.add(px)
        }
        data = list
        return list.toList()
    }

    override fun onPixelTapped(pixel: Pixel) {
        if (isPrimaryColourSelected) pixel.setBackgroundColor(primaryColour) else pixel.setBackgroundColor(
            secondaryColour)
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

class ColourPickerAdapter(private val list: List<Int>, private val caller: ColourPickerListener) : RecyclerView.Adapter<ColourPickerAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.colour_picker_layout,
                parent,
                false
            ) as View

        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.backgroundTintList = ColorStateList.valueOf(list[position])

        holder.view.setOnClickListener {
            caller.onColourTapped(list[position], it)
        }
    }

    override fun getItemCount() = list.size
}

