package com.realtomjoney.pyxlmoose.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ColorPalette(
    @ColumnInfo(name = "item_color_palette_name") val colorPaletteName: String,
    @ColumnInfo(name = "item_color_palette_color_data") val colorPaletteColorData: String,
    @ColumnInfo(name = "item_is_primary_color_palette") val isPrimaryColorPalette: Boolean = false) {
    @PrimaryKey(autoGenerate = true) var objId = 0
}