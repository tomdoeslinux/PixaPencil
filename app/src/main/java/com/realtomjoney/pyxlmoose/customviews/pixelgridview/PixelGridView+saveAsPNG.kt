package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
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
            showSnackbar("Successfully saved $projectTitle as PNG", SnackbarDuration.DEFAULT)
        } else {
            showSnackbar("Error saving $projectTitle as PNG", SnackbarDuration.DEFAULT)
        }
    }

    MediaScannerConnection.scanFile(context, arrayOf(file.path), arrayOf(defOutPathData), null)
}