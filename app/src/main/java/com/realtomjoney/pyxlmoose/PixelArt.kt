package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap
import android.view.View
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class PixelArt(
    val bitmap: Bitmap,
    val title: String,
    val pixelData: List<View>,
    var isFavourited: Boolean,
    val dateCreated: String = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()),)