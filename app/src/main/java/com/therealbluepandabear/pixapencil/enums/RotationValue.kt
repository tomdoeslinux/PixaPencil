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

/**
 * There is a bug in which when you rotate a view 180 degrees, its drop shadow disappears.
 * This has been reported to Google but it hasn't been fixed in some compat libraries.
 * Hopefully it gets fixed in other libraries soon, but for now we can simply rotate it by 179.9 degrees and it seems to fix it.
 * The difference isn't noticeable.
 */

enum class RotationValue(val degrees: Float, val clockwise: Boolean = false) {
    NinetyAntiClockwise(90f),
    NinetyClockwise(90f, true),
    OneEighty(180f),
}