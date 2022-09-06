/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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