package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.projectTitle
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.utility.FileHelperUtilities
import com.therealbluepandabear.pixapencil.utility.Flags
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsImage(format: Bitmap.CompressFormat) {
    val formatName = if (format == Bitmap.CompressFormat.PNG) "PNG" else "JPG"

    val fileHelperUtilitiesInstance = FileHelperUtilities.createInstance(this.context, outerCanvasInstance)

    fileHelperUtilitiesInstance.saveBitmapAsImage(90, format) { outputCode, _file, exceptionMessage_1 ->
        if (outputCode == OutputCode.Success) {
            file = _file

            showSnackbarWithAction(context.getString(R.string.snackbar_image_successfully_saved_in_code_str, projectTitle, formatName), SnackbarDuration.Medium, context.getString(R.string.snackbar_view_exception_info_button_text_in_code_str)) {
                fileHelperUtilitiesInstance.openImageFromUri(Uri.fromFile(file)) { outputCode, exceptionMessage_2 ->
                    if (outputCode == OutputCode.Failure) {
                        if (exceptionMessage_2 != null) {
                            showSnackbarWithAction(context.getString(R.string.dialog_view_file_error_title_in_code_str), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title_in_code_str)) {
                                (context as Activity)
                                    .showDialog(context.getString(R.string.dialog_exception_info_title_in_code_str), exceptionMessage_2, context.getString(R.string.dialog_positive_button_text_in_code_str), { _, _ -> }, null, null, null)
                            }
                        } else {
                            showSnackbar(context.getString(R.string.dialog_view_file_error_title_in_code_str), SnackbarDuration.Default)
                        }
                    } else {
                        Flags.PressedBackFromImg = true
                    }
                }
            }
        } else {
            if (exceptionMessage_1 != null) {
                showSnackbarWithAction(context.getString(R.string.dialog_image_exception_when_saving_title_in_code_str, projectTitle, formatName), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title_in_code_str)) {
                    (context as Activity)
                        .showDialog(context.getString(R.string.dialog_exception_info_title_in_code_str), exceptionMessage_1, context.getString(R.string.dialog_positive_button_text_in_code_str), { _, _ -> }, null, null, null)
                }
            } else {
                showSnackbar(context.getString(R.string.dialog_image_exception_when_saving_title_in_code_str, projectTitle, formatName), SnackbarDuration.Default)
            }
        }
    }
}