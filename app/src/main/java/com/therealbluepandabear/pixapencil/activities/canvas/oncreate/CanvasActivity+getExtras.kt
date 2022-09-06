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

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.extensions.getUriExtra
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.getExtras() {
    index = intent.getIntExtra(StringConstants.Extras.INDEX_EXTRA, -1)
    title = intent.getStringExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA)
    width = intent.getIntExtra(StringConstants.Extras.WIDTH_EXTRA, width)
    height = intent.getIntExtra(StringConstants.Extras.HEIGHT_EXTRA, height)
    spotLightInProgress = intent.getBooleanExtra(StringConstants.Extras.SPOTLIGHT_IN_PROGRESS_EXTRA, false)
    projectTitle = title?.toString() ?: projectTitle

    val uriStr = intent.getUriExtra(StringConstants.Extras.BITMAP_URI_EXTRA)

    if (uriStr != null) {
        uri = uriStr
    }

    if (index == -1) {
        viewModel.saved = false
    } else {
        width = AppData.pixelArtDB.dao().getAllNoLiveData()[index].width
        height = AppData.pixelArtDB.dao().getAllNoLiveData()[index].height
    }
}