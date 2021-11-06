package com.realtomjoney.pyxlmoose.activities.canvas

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
var pixelGridOn = true
var hasSaved = false

var isSelected = false
var background: Drawable? = null

var colorPickerMode = false

var wantsToChangeBackground = false

var currentBackground: Int? = null
var hasSetBackgroundYet = false

var isErasing = false

var isHorizontalMirrorEnabled = false
var isVerticalMirrorEnabled = false
