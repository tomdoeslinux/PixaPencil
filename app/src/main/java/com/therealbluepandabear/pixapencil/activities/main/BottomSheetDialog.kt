package com.therealbluepandabear.pixapencil.activities.main


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.therealbluepandabear.pixapencil.databinding.ActivityMainBottomSheetBinding
import com.therealbluepandabear.pixapencil.listeners.BottomSheetDialogListener
import com.therealbluepandabear.pixapencil.models.PixelArt

class BottomSheetDialog(private val pixelArt: PixelArt) : BottomSheetDialogFragment() {
    private lateinit var binding: ActivityMainBottomSheetBinding

    private lateinit var caller: BottomSheetDialogListener

    private fun setup() {
        binding.activityMainBottomSheetViewDetails.setOnClickListener {
            caller.onViewDetailsTapped(pixelArt)
        }

        binding.activityMainBottomSheetRename.setOnClickListener {
            caller.onRenameTapped(pixelArt)
        }

        binding.activityMainBottomSheetDelete.setOnClickListener {
            caller.onDeleteTapped(pixelArt)
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

        return binding.root
    }
}