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

package com.therealbluepandabear.pixapencil.extensions

import android.content.Intent
import android.net.Uri

fun Intent.putUriExtra(name: String, value: Uri): Intent {
    return putExtra(name, value.toString())
}

fun Intent.getUriExtra(name: String): Uri? {
    return if (getStringExtra(name) != null) {
        Uri.parse(getStringExtra(name))
    } else {
        null
    }
}