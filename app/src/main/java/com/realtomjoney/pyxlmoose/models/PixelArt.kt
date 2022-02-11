package com.realtomjoney.pyxlmoose.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class PixelArt(
    @ColumnInfo(name = "item_cover_bitmap") var coverBitmap: String,
    @ColumnInfo(name = "item_bitmap") var bitmap: String,
    @ColumnInfo(name = "item_width") var width: Int,
    @ColumnInfo(name = "item_height") var height: Int,
    @ColumnInfo(name = "item_rotation") var rotation: Float,
    @ColumnInfo(name = "item_title") var title: String,
    @ColumnInfo(name = "item_starred") var starred: Boolean,
    @ColumnInfo(name = "item_date_created") var dateCreated: String = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now())) {
    @PrimaryKey(autoGenerate = true) var objId = 0
}