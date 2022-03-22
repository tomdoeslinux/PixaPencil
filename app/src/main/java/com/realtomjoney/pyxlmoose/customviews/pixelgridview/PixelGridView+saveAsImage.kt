package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.enums.OutputCode
import com.realtomjoney.pyxlmoose.enums.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction
import com.realtomjoney.pyxlmoose.utility.FileHelperUtilities
import com.realtomjoney.pyxlmoose.utility.StringConstants
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