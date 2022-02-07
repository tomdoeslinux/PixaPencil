@file:Suppress("DEPRECATION")

package com.realtomjoney.pyxlmoose.utility

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.enums.OutputCode
import java.io.File
import java.io.FileOutputStream

object FileHelperUtilities {
    fun saveBitmapAsImage(compressionOutputQuality: Int, compressionFormat: Bitmap.CompressFormat, onTaskFinished: (OutputCode, File) -> Unit, context: Context) {
        // Thanks to https://stackoverflow.com/users/3571603/javatar on StackOverflow - quite a bit of the code is based off of their solution

        var outputCode = OutputCode.SUCCESS
        val pathData = "image/jpeg"
        val outputName = if (compressionFormat == Bitmap.CompressFormat.PNG) "$projectTitle.png" else "$projectTitle.jpg"

        val environmentRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
        val directory = File(environmentRoot)

        directory.mkdirs()

        val file = File(directory, outputName)

        try {
            val outputStream = FileOutputStream(file)
            val bitmapToCompress =  outerCanvasInstance.fragmentHost.drawToBitmap()
            bitmapToCompress.compress(compressionFormat, compressionOutputQuality, outputStream)
            outputStream.close()
        } catch (exception: Exception) {
            outputCode = OutputCode.FAILURE
        } finally {
            onTaskFinished(outputCode, file)
        }

        MediaScannerConnection.scanFile(context, arrayOf(file.path), arrayOf(pathData), null)
    }

    fun openImageFromUri(uri: Uri, onTaskFinished: (OutputCode) -> Unit, context: Context) {
        var outputCode = OutputCode.SUCCESS
        val intentAction = Intent.ACTION_VIEW
        val type = "image/*"

        val intent = Intent()
        intent.action = intentAction
        intent.setDataAndType(uri, type)

        try {
            context.startActivity(intent)
        } catch (exception: Exception) {
            outputCode = OutputCode.FAILURE
        } finally {
            onTaskFinished(outputCode)
        }
    }
}
