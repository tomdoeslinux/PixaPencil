package com.realtomjoney.pyxlmoose

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding
import java.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator





class MainActivity : AppCompatActivity(), RecentCreationsListener {

    private lateinit var binding: ActivityMainBinding
    private var hasNavigatedBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        binding.recentCreationsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 1) {
                    binding.floatingActionButton.animate().setDuration(200).scaleX(1f).scaleY(1f)
                        .setInterpolator(LinearOutSlowInInterpolator())
                    Toast.makeText(this@MainActivity, "Pog", Toast.LENGTH_LONG).show()
                    return
                }

                if (binding.floatingActionButton.isShown && dy > 2 || dy < 2) {
                    binding.floatingActionButton.hide()
                } else {
                    binding.floatingActionButton.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    binding.floatingActionButton.show()

                super.onScrollStateChanged(recyclerView, newState)
            }
        }) // Great solution by VelocityPulse

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
        binding.floatingActionButton.show()

        if (!hasNavigatedBack) {
            binding.recentCreationsRecyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.recentCreationsRecyclerView.adapter =
                RecentCreationsAdapter(SavedPixelArtDatabase.toList(), this)
        } else {
            binding.recentCreationsRecyclerView.adapter?.notifyDataSetChanged()
        }

        super.onResume()
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
        intent.putExtra("INDEX", SavedPixelArtDatabase.toList().indexOf(param))
        startActivity(intent)
    }
}

