package com.therealbluepandabear.pixapencil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class PixelArt(
    @ColumnInfo(name = "item_cover_bitmap_file_path") var coverBitmapFilePath: String,
    @ColumnInfo(name = "item_bitmap") var bitmap: String,
    @ColumnInfo(name = "item_width") var width: Int,
    @ColumnInfo(name = "item_height") var height: Int,
    @ColumnInfo(name = "item_title") var title: String,
    @ColumnInfo(name = "item_starred") var starred: Boolean,
    @ColumnInfo(name = "item_date_created") var dateCreated: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy\nHH:mm"))) {
    @PrimaryKey(autoGenerate = true) var objId = 0
}