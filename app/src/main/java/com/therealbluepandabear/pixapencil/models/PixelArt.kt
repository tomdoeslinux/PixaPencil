package com.therealbluepandabear.pixapencil.models

import android.os.Build
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getCurrentDateTime(): String {
    val dateTimeFormatPattern = "yyyy/MM/dd HH:mm:ss"

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeFormatPattern))
    } else {
        val SDFormat = SimpleDateFormat(dateTimeFormatPattern)
        SDFormat.format(Date())
    }
}
@Entity
data class PixelArt(
    @ColumnInfo(name = "item_cover_bitmap") var coverBitmap: String,
    @ColumnInfo(name = "item_bitmap") var bitmap: String,
    @ColumnInfo(name = "item_width") var width: Int,
    @ColumnInfo(name = "item_height") var height: Int,
    @ColumnInfo(name = "item_dimen_cw") var dimenCW: Int,
    @ColumnInfo(name = "item_dimen_ch") var dimenCH: Int,
    @ColumnInfo(name = "item_rotation") var rotation: Float,
    @ColumnInfo(name = "item_title") var title: String,
    @ColumnInfo(name = "item_starred") var starred: Boolean,
    @ColumnInfo(name = "item_date_created") var dateCreated: String = getCurrentDateTime()) {
    @PrimaryKey(autoGenerate = true) var objId = 0
}