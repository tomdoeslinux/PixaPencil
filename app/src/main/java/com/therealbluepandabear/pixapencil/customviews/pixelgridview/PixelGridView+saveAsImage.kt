package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.app.Activity
import android.net.Uri
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.*
import com.therealbluepandabear.pixapencil.extensions.rotate
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.general.BitmapCompressFormatUtilities
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsImage(
    format: BitmapCompressFormat,
    resolution: BitmapResolution,
    coordinatorLayout: CoordinatorLayout,
    projectTitle: String,
    flipMatrix: List<FlipValue>,
    compressionOutputQuality: Int = IntConstants.COMPRESSION_QUALITY_MAX) {
    val formatName = BitmapCompressFormatUtilities.getFormattedName(format)

    val bitmap = if (resolution == BitmapResolution.Scaled) {
        this.drawToBitmap().rotate((parent as View).rotation.toInt(), flipMatrix)
    } else {
        pixelGridViewBitmap
    }

    val fileHelperUtilitiesInstance = FileHelperUtilities.createInstance(context)

    fileHelperUtilitiesInstance.saveBitmapAsImage(bitmap, projectTitle,compressionOutputQuality, format) { outputCode, _file, exceptionMessage_1 ->
        if (outputCode == OutputCode.Success) {
            file = _file

            coordinatorLayout.showSnackbarWithAction(context.getString(R.string.snackbar_image_successfully_saved, projectTitle, formatName), SnackbarDuration.Medium, context.getString(R.string.snackbar_view_exception_info_button_text)) {
                fileHelperUtilitiesInstance.openImageFromUri(Uri.fromFile(file)) { outputCode, exceptionMessage_2 ->
                    if (outputCode == OutputCode.Failure) {
                        if (exceptionMessage_2 != null) {
                            coordinatorLayout.showSnackbarWithAction(context.getString(R.string.dialog_view_file_error_title), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title)) {
                                (context as Activity).showSimpleInfoDialog(context.getString(R.string.dialog_exception_info_title), exceptionMessage_2)
                            }
                        } else {
                            coordinatorLayout.showSnackbar(context.getString(R.string.dialog_view_file_error_title), SnackbarDuration.Default)
                        }
                    }
                }
            }
        } else {
            if (exceptionMessage_1 != null) {
                coordinatorLayout.showSnackbarWithAction(context.getString(R.string.dialog_image_exception_when_saving_title, projectTitle, formatName), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title)) {
                    (context as Activity).showSimpleInfoDialog(context.getString(R.string.dialog_exception_info_title), exceptionMessage_1)
                }
            } else {
                coordinatorLayout.showSnackbar(context.getString(R.string.dialog_image_exception_when_saving_title, projectTitle, formatName), SnackbarDuration.Default)
            }
        }
    }
}