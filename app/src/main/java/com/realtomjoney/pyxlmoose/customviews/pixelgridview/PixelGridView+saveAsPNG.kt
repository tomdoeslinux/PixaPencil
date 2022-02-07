@file:Suppress("DEPRECATION")

package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Bitmap
import android.net.Uri
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.enums.OutputCode
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction
import com.realtomjoney.pyxlmoose.utility.FileHelperUtilities
import java.io.File

lateinit var file: File

fun PixelGridView.extendedSaveAsPNG() {
   FileHelperUtilities.saveBitmapAsImage(90, Bitmap.CompressFormat.PNG, { outputCode, _file ->
       if (outputCode == OutputCode.SUCCESS) {
           file = _file

           showSnackbarWithAction("Successfully saved $projectTitle as PNG", SnackbarDuration.MEDIUM, "View") {
               FileHelperUtilities.openImageFromUri(Uri.fromFile(file), { outputCode ->
                   if (outputCode == OutputCode.FAILURE) {
                       showSnackbar("Error trying to view file", SnackbarDuration.DEFAULT)
                   }
               }, context)
           }
       } else {
           showSnackbar("Error saving $projectTitle as PNG", SnackbarDuration.DEFAULT)
       }
    }, context)
}