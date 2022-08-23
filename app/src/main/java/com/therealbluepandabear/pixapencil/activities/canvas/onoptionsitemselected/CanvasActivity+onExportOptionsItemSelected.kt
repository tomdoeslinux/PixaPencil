package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.app.AlertDialog
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.databinding.ExportProjectDialogLayoutBinding
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.BitmapResolution
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.utility.InputFilterMinMax
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

fun CanvasActivity.onExportOptionsItemSelected() {
    val exportLayoutBinding = ExportProjectDialogLayoutBinding.inflate(LayoutInflater.from(this))
    var selectedFileType: BitmapCompressFormat = BitmapCompressFormat.PNG

    exportLayoutBinding.root.post {
        // We do this so that there is no default top margin, which I personally find it ugly
        (exportLayoutBinding.root.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 0

        exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.setText(projectTitle)
        exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputEditText.setText(IntConstants.COMPRESSION_QUALITY_MAX.toString())
        exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.doAfterTextChanged {
            if (it.toString().isNotBlank()) {
                exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputLayout.isErrorEnabled = false
            } else {
                exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputLayout.error = getString(
                    R.string.exception_invalid_file_name)
            }
        }

        exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputEditText.filters = arrayOf(
            InputFilterMinMax(IntConstants.COMPRESSION_QUALITY_MIN, IntConstants.COMPRESSION_QUALITY_MAX)
        )

        exportLayoutBinding.exportProjectDialogLayoutRadioGroupFileType.setOnCheckedChangeListener { _, optionId ->
            if (optionId != R.id.exportProjectDialogLayout_radioButton_JPG) {
                exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputLayout.visibility = View.GONE
            } else {
                exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputLayout.visibility = View.VISIBLE
            }

            when (optionId) {
                R.id.exportProjectDialogLayout_radioButton_PNG -> {
                    selectedFileType = BitmapCompressFormat.PNG
                }

                R.id.exportProjectDialogLayout_radioButton_JPG -> {
                    selectedFileType = BitmapCompressFormat.JPEG

                    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && exportLayoutBinding.root is ScrollView) {
                        binding.root.post {
                            (exportLayoutBinding.root as ScrollView).scrollTo(0, binding.root.bottom)
                        }
                    }
                }

                R.id.exportProjectDialogLayout_radioButton_WEBP -> {
                    selectedFileType = BitmapCompressFormat.WEBP
                }

                R.id.exportProjectDialogLayout_radioButton_TIF -> {
                    selectedFileType = BitmapCompressFormat.TIFF
                }

                R.id.exportProjectDialogLayout_radioButton_BMP -> {
                    selectedFileType = BitmapCompressFormat.BMP
                }

                else -> {
                    selectedFileType = BitmapCompressFormat.PNG
                }
            }
        }
    }

    val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(R.string.activityCanvasTopAppMenu_export)
        .setView(exportLayoutBinding.root)
        .setCancelable(false)
        .setPositiveButton(R.string.generic_ok, null)
        .setNegativeButton(R.string.generic_cancel, null)

    val dialog = alertDialog.show()

    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
        val title = exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.text.toString()

        if (title.isNotBlank()) {
            val resolution: BitmapResolution =
                when (exportLayoutBinding.exportProjectDialogLayoutRadioGroupResolutionType.checkedRadioButtonId) {
                    R.id.exportProjectDialogLayout_radioButton_Raw -> {
                        BitmapResolution.Raw
                    }

                    R.id.exportProjectDialogLayout_radioButton_Scaled -> {
                        BitmapResolution.Scaled
                    }

                    else -> {
                        BitmapResolution.Raw
                    }
                }

            binding.activityCanvasPixelGridView.saveAsImage(
                selectedFileType,
                resolution,
                binding.activityCanvasCoordinatorLayout,
                exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.text.toString(),
                viewModel.flipMatrix,
                compressionOutputQuality = exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputEditText.text.toString().toIntOrNull() ?: IntConstants.COMPRESSION_QUALITY_MAX,
            ) {
                if (it == OutputCode.Cancelled) {
                    dialog.show()
                }
            }

            dialog.cancel()
        } else {
            exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputLayout.error = getString(R.string.exception_invalid_file_name)
        }
    }
}