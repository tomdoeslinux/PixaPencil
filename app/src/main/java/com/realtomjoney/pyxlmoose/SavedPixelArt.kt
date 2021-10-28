package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap
import android.view.View
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class SavedPixelArt(val bitmap: Bitmap, val title: String, val pixelData: List<View>, val dateCreated: String = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(
    LocalDateTime.now()))