package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding
import com.realtomjoney.pyxlmoose.fragments.CanvasFragment
import com.realtomjoney.pyxlmoose.fragments.ColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.FindAndReplaceFragment

lateinit var binding: ActivityCanvasBinding
var index: Int? = null

var primaryColor: Int = Color.BLACK
var secondaryColor: Int = Color.BLUE
var spanCount = 5
var isPrimaryColorSelected = true

var isSelected = false
var background: Drawable? = null

var currentBackground: Int? = null

var currentFragmentInstance: Fragment? = null

lateinit var colorPickerFragmentInstance: ColorPickerFragment
lateinit var canvasFragmentInstance: CanvasFragment
lateinit var findAndReplaceFragmentInstance: FindAndReplaceFragment
