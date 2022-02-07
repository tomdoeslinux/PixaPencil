@file:Suppress("DEPRECATION")

package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.enums.OutputCode
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showDialog
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction
import com.realtomjoney.pyxlmoose.utility.FileHelperUtilities
import com.realtomjoney.pyxlmoose.utility.StringConstants
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsPNG() {
   FileHelperUtilities.saveBitmapAsImage(90, Bitmap.CompressFormat.PNG, { outputCode, _file, exceptionMessage_1 ->
       if (outputCode == OutputCode.SUCCESS) {
           file = _file

           showSnackbarWithAction("Successfully saved $projectTitle as PNG", SnackbarDuration.MEDIUM, "View") {
               FileHelperUtilities.openImageFromUri(Uri.fromFile(file), { outputCode, exceptionMessage_2 ->
                   if (outputCode == OutputCode.FAILURE) {
                       showSnackbar("Error trying to view file", SnackbarDuration.DEFAULT)

                       if (exceptionMessage_2 != null) {
                           showSnackbarWithAction("Error trying to view file", SnackbarDuration.DEFAULT, "Exception Info") {
                               (context as Activity).showDialog("Exception Info", exceptionMessage_2, StringConstants.DIALOG_POSITIVE_BUTTON_TEXT, { _, _-> }, null, null, null)
                           }
                       } else {
                           showSnackbar("Error trying to view file", SnackbarDuration.DEFAULT)
                       }
                   }
               }, context)
           }
       } else {
           if (exceptionMessage_1 != null) {
               showSnackbarWithAction("Error saving $projectTitle as PNG", SnackbarDuration.DEFAULT, "Exception Info") {
                   (context as Activity).showDialog("Exception Info", exceptionMessage_1, StringConstants.DIALOG_POSITIVE_BUTTON_TEXT, { _, _-> }, null, null, null)
               }
           } else {
               showSnackbar("Error saving $projectTitle as PNG", SnackbarDuration.DEFAULT)
           }
       }
    }, context)
}