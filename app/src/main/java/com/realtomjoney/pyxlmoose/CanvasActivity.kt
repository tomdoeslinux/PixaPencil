package com.realtomjoney.pyxlmoose

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.EditText
import android.widget.Toast
import android.widget.LinearLayout




var colour: Int = Color.BLACK
var spanCount = 5
const val TOAST_MESSAGE = "Please name your project."

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener {
    private lateinit var binding: ActivityCanvasBinding

    private var data = listOf<Pixel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spanCount = intent.getIntExtra("SPAN_COUNT", spanCount)

        setBindings()
        setUpFragment()
        setUpRecyclerView()
        setOnClickListeners()
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
            getHexDialogBuilder("Hexadecimal", "Input a hexadecimal value", "Hex value").create().show()
        }

        binding.chooseColourFromRGBButton.setOnClickListener {
            getRGBDialogBuilder("RGB", "Input an RGB value", listOf("R", "G", "B")).create().show()
        }
    }

    private fun getHexDialogBuilder(title: String, msg: String, hint: String): AlertDialog.Builder {
        val builder = AlertDialog.Builder(this)

        builder.setTitle(title)
        builder.setMessage(msg)

        val input = EditText(this)
        input.hint = hint

        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            colour = Color.parseColor(input.text.toString())
        }

        builder.setNegativeButton("Cancel") { _, _ -> }

        return builder
    }

    private fun getRGBDialogBuilder(title: String, msg: String, hints: List<String>): AlertDialog.Builder {
        val editTextNum: Int = hints.size

        val builder = AlertDialog.Builder(this)

        builder.setTitle(title)
        builder.setMessage(msg)

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
            colour = Color.argb(
                100,
                editTexts[0].text.toString().toInt(),
                editTexts[0].text.toString().toInt(),
                editTexts[0].text.toString().toInt(),
            )
        }

        builder.setNegativeButton("Cancel") { _, _ -> }

        return builder
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.colourPickerRecyclerView.layoutManager = layoutManager

        binding.colourPickerRecyclerView.adapter = ColourPickerAdapter(listOf(
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.BLACK, Color.argb(100, 255, 69, 0)))
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
        try {
            pixel.setBackgroundColor(colour)
        } catch (e: Exception) {
        }
    }
}

class ColourPickerAdapter(private val list: List<Int>) : RecyclerView.Adapter<ColourPickerAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

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
            colour = list[position]

            isSelected = if (!isSelected) {
                updateColourSelectedIndicator(it)
                true
            } else {
                updateColourSelectedIndicator(it)
                false
            }
        }
    }

    override fun getItemCount() = list.size
}

