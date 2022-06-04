package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.app.Activity
import android.net.Uri
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.utility.general.BitmapCompressFormatUtilities
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsImage(format: BitmapCompressFormat) {
    val formatName = BitmapCompressFormatUtilities.getFormattedName(format)

    val bitmap = outerCanvasInstance.drawFragmentHostToBitmap()
    val fileHelperUtilitiesInstance = FileHelperUtilities.createInstance(context)

    var cntxView = if (context is CanvasActivity) {
        binding.clayout
    } else this

    if (cntxView == null) {
        cntxView = this
    }

    fileHelperUtilitiesInstance.saveBitmapAsImage(bitmap, projectTitle,90, format) { outputCode, _file, exceptionMessage_1 ->
        if (outputCode == OutputCode.Success) {
            file = _file

            cntxView.showSnackbarWithAction(context.getString(R.string.snackbar_image_successfully_saved_in_code_str, projectTitle, formatName), SnackbarDuration.Medium, context.getString(R.string.snackbar_view_exception_info_button_text_in_code_str)) {
                fileHelperUtilitiesInstance.openImageFromUri(Uri.fromFile(file)) { outputCode, exceptionMessage_2 ->
                    if (outputCode == OutputCode.Failure) {
                        if (exceptionMessage_2 != null) {
                            cntxView.showSnackbarWithAction(context.getString(R.string.dialog_view_file_error_title_in_code_str), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title_in_code_str)) {
                                (context as Activity).showSimpleInfoDialog(context.getString(R.string.dialog_exception_info_title_in_code_str), exceptionMessage_2)
                            }
                        } else {
                            cntxView.showSnackbar(context.getString(R.string.dialog_view_file_error_title_in_code_str), SnackbarDuration.Default)
                        }
                    }
                }
            }
        } else {
            if (exceptionMessage_1 != null) {
                cntxView.showSnackbarWithAction(context.getString(R.string.dialog_image_exception_when_saving_title_in_code_str, projectTitle, formatName), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title_in_code_str)) {
                    (context as Activity).showSimpleInfoDialog(context.getString(R.string.dialog_exception_info_title_in_code_str), exceptionMessage_1)
                }
            } else {
                cntxView.showSnackbar(context.getString(R.string.dialog_image_exception_when_saving_title_in_code_str, projectTitle, formatName), SnackbarDuration.Default)
            }
        }
    }
}