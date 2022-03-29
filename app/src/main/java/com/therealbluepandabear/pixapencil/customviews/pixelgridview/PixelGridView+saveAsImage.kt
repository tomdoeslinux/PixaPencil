package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import com.therealbluepandabear.pixapencil.activities.canvas.projectTitle
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.utility.FileHelperUtilities
import com.therealbluepandabear.pixapencil.utility.StringConstants
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsImage(format: Bitmap.CompressFormat) {
    val formatName = if (format == Bitmap.CompressFormat.PNG) "PNG" else "JPG"

    val fileHelperUtilitiesInstance = FileHelperUtilities.createInstanceFromContext(this.context)

    fileHelperUtilitiesInstance.saveBitmapAsImage(90, format) { outputCode, _file, exceptionMessage_1 ->
        if (outputCode == OutputCode.Success) {
            file = _file

            showSnackbarWithAction("Successfully saved $projectTitle as $formatName", SnackbarDuration.Medium, StringConstants.SnackbarViewExceptionInfoButtonText) {
                fileHelperUtilitiesInstance.openImageFromUri(Uri.fromFile(file)) { outputCode, exceptionMessage_2 ->
                    if (outputCode == OutputCode.Failure) {
                        if (exceptionMessage_2 != null) {
                            showSnackbarWithAction(StringConstants.DialogViewFileErrorTitle, SnackbarDuration.Default, StringConstants.DialogExceptionInfoTitle) {
                                (context as Activity)
                                    .showDialog(StringConstants.DialogExceptionInfoTitle, exceptionMessage_2, StringConstants.DialogPositiveButtonText, { _, _ -> }, null, null, null)
                            }
                        } else {
                            showSnackbar(StringConstants.DialogViewFileErrorTitle, SnackbarDuration.Default)
                        }
                    }
                }
            }
        } else {
            if (exceptionMessage_1 != null) {
                showSnackbarWithAction("Error saving $projectTitle as $formatName", SnackbarDuration.Default, StringConstants.DialogExceptionInfoTitle) {
                    (context as Activity)
                        .showDialog(StringConstants.DialogExceptionInfoTitle, exceptionMessage_1, StringConstants.DialogPositiveButtonText, { _, _ -> }, null, null, null)
                }
            } else {
                showSnackbar("Error saving $projectTitle as $formatName", SnackbarDuration.Default)
            }
        }
    }
}