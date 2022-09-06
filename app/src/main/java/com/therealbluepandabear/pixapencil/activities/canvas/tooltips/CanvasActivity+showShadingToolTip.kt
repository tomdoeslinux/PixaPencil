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

package com.therealbluepandabear.pixapencil.activities.canvas.tooltips

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowShadingToolTipValueFromPreference
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithActionAndCallback
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.showShadingToolTip() {
    binding.activityCanvasCoordinatorLayout.showSnackbarWithActionAndCallback(
        getString(R.string.tool_tip_shading),
        SnackbarDuration.Medium,
        getString(R.string.tool_tip_dont_show_again),
        {
            with(sharedPreferenceObject.edit()) {
                putBoolean(
                    StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SHADING_TOOLTIP_IDENTIFIER,
                    false
                )
                apply()
            }
            applyShowShadingToolTipValueFromPreference()
        }
    ) {
        with(sharedPreferenceObject.edit()) {
            putBoolean(
                StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SHADING_TOOLTIP_IDENTIFIER,
                false
            )
            apply()
        }
        applyShowShadingToolTipValueFromPreference()
    }
}