@file:Suppress("DEPRECATION")

package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction
import java.io.File
import java.io.FileOutputStream


enum class BitmapCompressionOutputCode {
    SUCCESS,
    FAILURE
}

// Thanks to https://stackoverflow.com/users/3571603/javatar on StackOverflow - quite a bit of the code is based off of their solution

fun PixelGridView.extendedSaveAsPNG() {
    val defOutQuality = 90
    val defOutCompressFormat = Bitmap.CompressFormat.PNG
    val defOutPathData = "image/jpeg"
    var outputCode = BitmapCompressionOutputCode.SUCCESS

    val environmentRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
    val directory = File(environmentRoot)

    directory.mkdirs()

    val outputName = "$projectTitle.png"
    val file = File(directory, outputName)

    try {
        val outputStream = FileOutputStream(file)
        outerCanvasInstance.fragmentHost.drawToBitmap().compress(defOutCompressFormat, defOutQuality, outputStream)
        outputStream.close()
    } catch (exception: Exception) {
        outputCode = BitmapCompressionOutputCode.FAILURE
    } finally {
        if (outputCode == BitmapCompressionOutputCode.SUCCESS) {
            showSnackbarWithAction("Successfully saved $projectTitle as PNG", SnackbarDuration.MEDIUM, "View") {
                val defIntentAction = Intent.ACTION_VIEW
                val defType = "image/*"
                val fileAsUri = Uri.fromFile(file)

                val intent = Intent()
                intent.action = defIntentAction
                intent.setDataAndType(fileAsUri, defType)
                this.context.startActivity(intent)
            }
        } else {
            showSnackbar("Error saving $projectTitle as PNG", SnackbarDuration.DEFAULT)
        }
    }

    MediaScannerConnection.scanFile(context, arrayOf(file.path), arrayOf(defOutPathData), null)
}