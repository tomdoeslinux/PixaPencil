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

    private var colourData = listOf(
        Color.parseColor("#0048BA"),
        Color.parseColor("#B0BF1A"),
        Color.parseColor("#7CB9E8"),
        Color.parseColor("#C0E8D5"),
        Color.parseColor("#B284BE"),
        Color.parseColor("#0048BA"),
        Color.parseColor("#72A0C1"),
        Color.parseColor("#EDEAE0"),
        Color.parseColor("#F0F8FF"),
        Color.parseColor("#C46210"),
        Color.parseColor("#EFDECD"),
        Color.parseColor("#E52B50"),
        Color.parseColor("#9F2B68"),
        Color.parseColor("#F19CBB"),
        Color.parseColor("#AB274F"),
        Color.parseColor("#D3212D"),
        Color.parseColor("#3B7A57"),
        Color.parseColor("#FFBF00"),
        Color.parseColor("#FF7E00"),
        Color.parseColor("#9966CC"),
        Color.parseColor("#3DDC84"),
        Color.parseColor("#CD9575"),
        Color.parseColor("#665D1E"),
        Color.parseColor("#915C83"),
        Color.parseColor("#841B2D"),
        Color.parseColor("#FAEBD7"),
        Color.parseColor("#008000"),
        Color.parseColor("#8DB600"),
        Color.parseColor("#FBCEB1"),
        Color.parseColor("#00FFFF"),
        Color.parseColor("#7FFFD4"),
        Color.parseColor("#D0FF14"),
        Color.parseColor("#4B5320"),
        Color.parseColor("#8F9779"),
        Color.parseColor("#E9D66B"),
        Color.parseColor("#B2BEB5"),
        Color.parseColor("#87A96B"),
        Color.parseColor("#FF9966"),
        Color.parseColor("#A52A2A"),
        Color.parseColor("#FDEE00"),
        Color.parseColor("#568203"),
        Color.parseColor("#007FFF"),
        Color.parseColor("#F0FFFF"),
        Color.parseColor("#89CFF0"),
        Color.parseColor("#A1CAF1"),
        Color.parseColor("#F4C2C2"),
        Color.parseColor("#FEFEFA"),
        Color.parseColor("#FF91AF"),
        Color.parseColor("#FAE7B5"),
        Color.parseColor("#DA1884"),
        Color.parseColor("#7C0A02"),
        Color.parseColor("#848482"),
        Color.parseColor("#BCD4E6"),
        Color.parseColor("#9F8170"),
        Color.parseColor("#F5F5DC"),
        Color.parseColor("#2E5894"),
        Color.parseColor("#9C2542"),
        Color.parseColor("#FFE4C4"),
        Color.parseColor("#3D2B1F"),
        Color.parseColor("#967117"),
        Color.parseColor("#CAE00D"),
        Color.parseColor("#BFFF00"),
        Color.parseColor("#FE6F5E"),
        Color.parseColor("#BF4F51"),
        Color.parseColor("#000000"),
        Color.parseColor("#3D0C02"),
        Color.parseColor("#1B1811"),
        Color.parseColor("#3B2F2F"),
        Color.parseColor("#54626F"),
        Color.parseColor("#3B3C36"),
        Color.parseColor("#BFAFB2"),
        Color.parseColor("#FFEBCD"),
        Color.parseColor("#A57164"),
        Color.parseColor("#318CE7"),
        Color.parseColor("#ACE5EE"),
        Color.parseColor("#FAF0BE"),
        Color.parseColor("#660000"),
        Color.parseColor("#0000FF"),
        Color.parseColor("#1F75FE"),
        Color.parseColor("#0093AF"),
        Color.parseColor("#0087BD"),
        Color.parseColor("#0018A8"),
        Color.parseColor("#333399"),
        Color.parseColor("#0247FE"),
        Color.parseColor("#A2A2D0"),
        Color.parseColor("#6699CC"),
        Color.parseColor("#0D98BA"),
        Color.parseColor("#064E40"),
        Color.parseColor("#5DADEC"),
        Color.parseColor("#126180"),
        Color.parseColor("#8A2BE2"),
        Color.parseColor("#7366BD"),
        Color.parseColor("#4D1A7F"),
        Color.parseColor("#5072A7"),
        Color.parseColor("#F5F5DC"),
        Color.parseColor("#3C69E7"),
        Color.parseColor("#DE5D83"),
        Color.parseColor("#79443B"),
        Color.parseColor("#E3DAC9"),
        Color.parseColor("#006A4E"),
        Color.parseColor("#87413F"),
        Color.parseColor("#CB4154"),
        Color.parseColor("#66FF00"),
        Color.parseColor("#D891EF"),
        Color.parseColor("#C32148"),
        Color.parseColor("#1974D2"),
        Color.parseColor("#FFAA1D"),
        Color.parseColor("#FF55A3"),
        Color.parseColor("#FB607F"),
        Color.parseColor("#004225"),
        Color.parseColor("#CD7F32"),
        Color.parseColor("#88540B"),
        Color.parseColor("#AF6E4D"),
        Color.parseColor("#1B4D3E"),
        Color.parseColor("#7BB661"),
        Color.parseColor("#FFC680"),
        Color.parseColor("#800020"),
        Color.parseColor("#DEB887"),
        Color.parseColor("#A17A74"),
        Color.parseColor("#CC5500"),
        Color.parseColor("#E97451"),
        Color.parseColor("#8A3324"),
        Color.parseColor("#BD33A4"),
        Color.parseColor("#702963"),
        Color.parseColor("#536872"),
        Color.parseColor("#5F9EA0"),
        Color.parseColor("#A9B2C3"),
        Color.parseColor("#91A3B0"),
        Color.parseColor("#006B3C"),
        Color.parseColor("#ED872D"),
        Color.parseColor("#E30022"),
        Color.parseColor("#FFF600"),
        Color.parseColor("#A67B5B"),
        Color.parseColor("#4B3621"),
        Color.parseColor("#A3C1AD"),
        Color.parseColor("#C19A6B"),
        Color.parseColor("#EFBBCC"),
        Color.parseColor("#FFFF99"),
        Color.parseColor("#FFEF00"),
        Color.parseColor("#FF0800"),
        Color.parseColor("#E4717A"),
        Color.parseColor("#00BFFF"),
        Color.parseColor("#592720"),
        Color.parseColor("#C41E3A"),
        Color.parseColor("#00CC99"),
        Color.parseColor("#960018"),
        Color.parseColor("#D70040"),
        Color.parseColor("#FFA6C9"),
        Color.parseColor("#B31B1B"),

        )

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

