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

package com.therealbluepandabear.pixapencil.enums

import android.graphics.Bitmap
import android.os.Build

enum class BitmapCompressFormat(val correspondingEnum: () -> Bitmap.CompressFormat?) {
    PNG ({
        Bitmap.CompressFormat.PNG
    }),

    JPEG({
        Bitmap.CompressFormat.JPEG
    }),

    WEBP({
        Bitmap.CompressFormat.WEBP
    }),

    WEBP_LOSSLESS({
        if (Build.VERSION.SDK_INT >= 30) {
            Bitmap.CompressFormat.WEBP_LOSSLESS
        } else {
            Bitmap.CompressFormat.WEBP
        }
    }),

    TIFF({
        null
    }),

    BMP({
        null
    })
}