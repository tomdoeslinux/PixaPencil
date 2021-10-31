package com.realtomjoney.pyxlmoose.activity.canvas

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import com.realtomjoney.pyxlmoose.databinding.ActivityCanvasBinding

lateinit var binding: ActivityCanvasBinding
var data = listOf<View>()
var index: Int? = null

var primaryColour: Int = Color.BLACK
var secondaryColour: Int = Color.BLUE
var spanCount = 5
var isPrimaryColourSelected = true
var isMirrorMode = false
var pixelGridOn = true
var hasSaved = false

var isSelected = false
var background: Drawable? = null