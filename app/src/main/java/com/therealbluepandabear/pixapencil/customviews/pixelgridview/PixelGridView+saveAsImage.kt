package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.app.Activity
import android.net.Uri
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.rotate
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.utility.general.BitmapCompressFormatUtilities
import com.therealbluepandabear.pixapencil.utility.general.FileHelperUtilities
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsImage(format: BitmapCompressFormat, coordinatorLayout: CoordinatorLayout, projectTitle: String) {
    val formatName = BitmapCompressFormatUtilities.getFormattedName(format)

    val rotation = if (parent is View) {
        (parent as View).rotation.toInt()
    } else {
        0
    }

    val bitmap = this.drawToBitmap().rotate(rotation)
    val fileHelperUtilitiesInstance = FileHelperUtilities.createInstance(context)

    fileHelperUtilitiesInstance.saveBitmapAsImage(bitmap, projectTitle,90, format) { outputCode, _file, exceptionMessage_1 ->
        if (outputCode == OutputCode.Success) {
            file = _file

            coordinatorLayout.showSnackbarWithAction(context.getString(R.string.snackbar_image_successfully_saved_in_code_str, projectTitle, formatName), SnackbarDuration.Medium, context.getString(R.string.snackbar_view_exception_info_button_text_in_code_str)) {
                fileHelperUtilitiesInstance.openImageFromUri(Uri.fromFile(file)) { outputCode, exceptionMessage_2 ->
                    if (outputCode == OutputCode.Failure) {
                        if (exceptionMessage_2 != null) {
                            coordinatorLayout.showSnackbarWithAction(context.getString(R.string.dialog_view_file_error_title_in_code_str), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title_in_code_str)) {
                                (context as Activity).showSimpleInfoDialog(context.getString(R.string.dialog_exception_info_title_in_code_str), exceptionMessage_2)
                            }
                        } else {
                            coordinatorLayout.showSnackbar(context.getString(R.string.dialog_view_file_error_title_in_code_str), SnackbarDuration.Default)
                        }
                    }
                }
            }
        } else {
            if (exceptionMessage_1 != null) {
                coordinatorLayout.showSnackbarWithAction(context.getString(R.string.dialog_image_exception_when_saving_title_in_code_str, projectTitle, formatName), SnackbarDuration.Default, context.getString(R.string.dialog_exception_info_title_in_code_str)) {
                    (context as Activity).showSimpleInfoDialog(context.getString(R.string.dialog_exception_info_title_in_code_str), exceptionMessage_1)
                }
            } else {
                coordinatorLayout.showSnackbar(context.getString(R.string.dialog_image_exception_when_saving_title_in_code_str, projectTitle, formatName), SnackbarDuration.Default)
            }
        }
    }
}