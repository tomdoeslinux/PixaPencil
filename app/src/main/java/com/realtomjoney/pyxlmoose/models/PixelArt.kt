package com.realtomjoney.pyxlmoose.models

import androidx.room.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class PixelArts(
    @ColumnInfo(name = "item_bitmap") var bitmap: String,
    @ColumnInfo(name = "item_title") var title: String,
    @ColumnInfo(name = "item_pixel_data") var pixelData: String,
    @ColumnInfo(name = "item_favourited") var favourited: Boolean,
    @ColumnInfo(name = "item_date_created") var dateCreated: String = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now())) {
    @PrimaryKey(autoGenerate = true) var objId = 0
}