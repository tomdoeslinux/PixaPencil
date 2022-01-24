package com.realtomjoney.pyxlmoose.fragments.colorpicker.picker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.activities.canvas.showMenuItems
import com.realtomjoney.pyxlmoose.customviews.colorpickerview.colorPickerColorSelected
import com.realtomjoney.pyxlmoose.databinding.FragmentColorPickerPickerBinding
import com.realtomjoney.pyxlmoose.fragments.colorpicker.caller
import com.realtomjoney.pyxlmoose.fragments.colorpicker.colorPaletteMode_
import com.realtomjoney.pyxlmoose.fragments.colorpicker.oldColor_
import com.realtomjoney.pyxlmoose.fragments.colorpicker.picker.canvas.ColorPickerPickerCanvasFragment
import java.util.*


class ColorPickerPickerFragment : Fragment(), View.OnTouchListener {
    private fun setup() {
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(oldColor_)
    }

    private fun updatePreview() {
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(colorPickerColorSelected)
    }

    private fun hideKeyboard() {
        try {
            val inputMethodManager =
                (Objects.requireNonNull(requireActivity()).getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as InputMethodManager)
            inputMethodManager.hideSoftInputFromWindow(
                this.requireActivity().currentFocus!!.windowToken, 0
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun setOnClickListeners() {
        binding.fragmentColorPickerPickerDoneButton.setOnClickListener {
            hideKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    caller.onDoneButtonPressed((binding.fragmentColorPickerPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
                } catch (exception: Exception) {

                } finally {
                    showMenuItems()
                }
            }, 40)
        }
    }


    companion object {
        fun newInstance() = ColorPickerPickerFragment()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding_ = FragmentColorPickerPickerBinding.inflate(inflater, container, false)

        setup()
        setOnClickListeners()
        requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentColorPickerPicker_canvasFragmentHost, ColorPickerPickerCanvasFragment.newInstance()).commit()

        binding.fragmentColorPickerPickerCanvasFragmentHost.setOnTouchListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        val coordinateX = event.x.toInt()
        val coordinateY = event.y.toInt()

        val bitmap = v!!.drawToBitmap()

        return when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                if (coordinateX in 0 until bitmap.width && coordinateY in 0 until bitmap.height) {
                    colorPickerColorSelected = bitmap.getPixel(coordinateX, coordinateY)
                    updatePreview()
                    true
                } else false
            }
            MotionEvent.ACTION_DOWN -> {
                if (coordinateX in 0 until bitmap.width && coordinateY in 0 until bitmap.height) {
                    colorPickerColorSelected = bitmap.getPixel(coordinateX, coordinateY)
                    updatePreview()
                    true
                } else false
            }
            else -> {
                false
            }
        }
    }
}