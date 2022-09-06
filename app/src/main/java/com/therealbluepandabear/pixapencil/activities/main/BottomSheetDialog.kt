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

package com.therealbluepandabear.pixapencil.activities.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.therealbluepandabear.pixapencil.databinding.ActivityMainBottomSheetBinding
import com.therealbluepandabear.pixapencil.listeners.BottomSheetDialogListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

class BottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding: ActivityMainBottomSheetBinding
    private lateinit var caller: BottomSheetDialogListener
    private lateinit var pixelArt: PixelArt

    fun setParams(pixelArt: PixelArt) {
        this.pixelArt = pixelArt
    }

    companion object {
        fun newInstance(pixelArt: PixelArt): BottomSheetDialog {
            val bottomSheetDialog = BottomSheetDialog()
            bottomSheetDialog.setParams(pixelArt)

            return bottomSheetDialog
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(StringConstants.Identifiers.BOTTOM_SHEET_CAPTURED_PIXEL_ART, Gson().toJson(pixelArt))
    }

    private fun setup() {
        binding.activityMainBottomSheetDuplicate.setOnClickListener {
            caller.onDuplicateTapped(pixelArt, this)
        }

        binding.activityMainBottomSheetViewDetails.setOnClickListener {
            caller.onViewDetailsTapped(pixelArt)
        }

        binding.activityMainBottomSheetDelete.setOnClickListener {
            caller.onDeleteTapped(pixelArt, this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomSheetDialogListener) caller = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityMainBottomSheetBinding.inflate(LayoutInflater.from(context))

        setup()

        val bsDialog = (dialog as com.google.android.material.bottomsheet.BottomSheetDialog)
        bsDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            val extractedJson = Gson().fromJson(savedInstanceState.getString(StringConstants.Identifiers.BOTTOM_SHEET_CAPTURED_PIXEL_ART), PixelArt::class.java)
            pixelArt = extractedJson
        }
    }
}