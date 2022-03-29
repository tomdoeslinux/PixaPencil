@file:Suppress("DEPRECATION")

package com.therealbluepandabear.pixapencil.utility

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pixapencil.activities.canvas.projectTitle
import com.therealbluepandabear.pixapencil.enums.OutputCode
import java.io.File
import java.io.FileOutputStream

class FileHelperUtilities(private val context: Context) {
    companion object {
        fun createInstanceFromContext(context: Context) = FileHelperUtilities(context)
    }

    fun saveBitmapAsImage(compressionOutputQuality: Int,
                          compressionFormat: Bitmap.CompressFormat,
                          onTaskFinished: (OutputCode, File, String?) -> Unit) {
        /** Thank you to to javatar on StackOverflow - quite a bit of the code here is based off of their solution.
         *
         * - [Link to javatar's profile](https://stackoverflow.com/users/2033223/javatar)
         * - [Original StackOverFlow post](https://stackoverflow.com/users/3571603/)
         * **/

        var exceptionMessage: String? = null
        var outputCode = OutputCode.Success
        val pathData = "image/jpeg"
        val outputName = if (compressionFormat == Bitmap.CompressFormat.PNG) "$projectTitle.png" else "$projectTitle.jpg"

        val environmentRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
        val directory = File(environmentRoot)

        directory.mkdirs()

        val file = File(directory, outputName)

        try {
            val outputStream = FileOutputStream(file)
            val bitmapToCompress = outerCanvasInstance.fragmentHost.drawToBitmap()
            bitmapToCompress.compress(compressionFormat, compressionOutputQuality, outputStream)
            outputStream.close()
        } catch (exception: Exception) {
            exceptionMessage = exception.message
            outputCode = OutputCode.Failure
        } finally {
            onTaskFinished(outputCode, file, exceptionMessage)
        }

        MediaScannerConnection.scanFile(context, arrayOf(file.path), arrayOf(pathData), null)
    }

    fun openImageFromUri(uri: Uri,
                         onTaskFinished: (OutputCode, String?) -> Unit) {
        var exceptionMessage: String? = null
        var outputCode = OutputCode.Success
        val intentAction = Intent.ACTION_VIEW
        val type = "image/*"

        val intent = Intent().apply {
            action = intentAction
            setDataAndType(uri, type)
        }

        try {
            context.startActivity(intent)
        } catch (exception: Exception) {
            exceptionMessage = exception.message
            outputCode = OutputCode.Failure
        } finally {
            onTaskFinished(outputCode, exceptionMessage)
        }
    }
}
