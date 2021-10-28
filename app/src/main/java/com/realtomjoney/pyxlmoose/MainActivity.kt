package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity(), RecentCreationsListener {

    private lateinit var binding: ActivityMainBinding
    private var hasNavigatedBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        binding.floatingActionButton.setOnClickListener {
            val dialogueEditText = EditText(this)
            dialogueEditText.hint = "Span count"

            val builder = MaterialAlertDialogBuilder(this)
                .setTitle("Span Count")
                .setMessage("Please input an appropriate span count value:")
                .setView(dialogueEditText)
                .setPositiveButton("Done") { _, _ ->
                    startActivity(
                        Intent(this, CanvasActivity::class.java)
                            .putExtra("SPAN_COUNT", Integer.parseInt(dialogueEditText.text.toString())))
                }
                .setNegativeButton("Back") { _, _ -> }
            builder.show()
        }
        setGreetingText()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        if (!hasNavigatedBack) {
            binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.recentCreationsRecyclerView.adapter =
                RecentCreationsAdapter(BitmapDatabase.toList(), this)
        } else {
            binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun setGreetingText() {
        binding.titleTextView.text = setGreeting()
    }

    private fun setGreeting(): String {
        val calendar = Calendar.getInstance()

        when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> return "Good Morning"
            in 12..21 -> return "Good Afternoon"
            in 21..24 -> return "Good Night"
        }
        return ""
    }

    private fun setBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreationTapped(param: SavedPixelArt) {
        val intent = Intent(this, CanvasActivity::class.java)
        intent.putExtra("INDEX", BitmapDatabase.toList().indexOf(param))
        startActivity(intent)
    }
}

