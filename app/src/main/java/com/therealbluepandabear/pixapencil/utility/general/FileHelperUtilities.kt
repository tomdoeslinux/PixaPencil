/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

@file:Suppress("DEPRECATION")

package com.therealbluepandabear.pixapencil.utility.general

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.ParcelFileDescriptor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.IOException


class FileHelperUtilities(private val context: Context) {
    companion object {
        fun createInstance(context: Context) = FileHelperUtilities(context)
    }

    private fun commonDocumentDirPath(): File? {
        /** Thank you to to Noor Hossain on StackOverflow for this solution.
         *
         * - [Link to Noor Hossain's profile](https://stackoverflow.com/users/7608371/noor-hossain)
         * - [Original StackOverFlow post](https://stackoverflow.com/questions/65637610/saving-files-in-android-11-to-external-storagesdk-30)
         * **/

        var dir: File?
        dir =
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString() + "/" + context.getString(R.string.app_name)
            )


        if (!dir.exists()) {
            val success = dir.mkdirs()
            if (!success) {
                dir = null
            }
        }
        return dir
    }

    fun saveBitmapAsImage(
        bitmap: Bitmap,
        projectTitle: String?,
        compressionOutputQuality: Int,
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

        val extension = if (compressionFormat == Bitmap.CompressFormat.PNG) {
            "png"
        } else {
            "jpg"
        }

        val outputName = "$projectTitle.$extension"

        val directory: File? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            commonDocumentDirPath()
        } else {
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/" + context.getString(R.string.app_name))
        }

        directory?.mkdirs()

        val file = File(directory, outputName)

        fun createNewFile(file_: File, bitmap_: Bitmap = bitmap) {
            var bitmap2 = bitmap_

            if (compressionFormat == Bitmap.CompressFormat.PNG || compressionFormat == Bitmap.CompressFormat.JPEG) {
                try {
                    val outputStream = FileOutputStream(file_)

                    if (compressionFormat == Bitmap.CompressFormat.JPEG) {
                        val newBitmap = Bitmap.createBitmap(
                            bitmap2.width,
                            bitmap2.height,
                            bitmap2.config
                        )
                        val canvas = Canvas(newBitmap)
                        canvas.drawColor(Color.WHITE)
                        canvas.drawBitmap(bitmap2, 0f, 0f, null)

                        bitmap2 = newBitmap
                    }

                    bitmap2.compress(compressionFormat, compressionOutputQuality, outputStream)
                    outputStream.close()
                } catch (exception: Exception) {
                    exceptionMessage = exception.message
                    outputCode = OutputCode.Failure
                } finally {
                    onTaskFinished(outputCode, file_, exceptionMessage)
                }
            }

            MediaScannerConnection.scanFile(context, arrayOf(file_.path), arrayOf(pathData), null)
        }


        if (file.exists() && context.activity() != null) {
            val alertDialog = MaterialAlertDialogBuilder(context.activity()!!, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.dialog_file_exists_title)
                .setMessage(context.getString(R.string.dialog_file_exists_message, outputName))
                .setPositiveButton(R.string.dialog_file_exists_positive_button_text) { _, _ ->
                    createNewFile(file)
                }
                .setNegativeButton(R.string.generic_cancel) { _, _ ->
                    onTaskFinished(OutputCode.Cancelled, file, null)
                }

            alertDialog.show()
        } else {
            createNewFile(file)
        }
    }

    fun openImageFromUri(uri: Uri,
                         onTaskFinished: (OutputCode, String?) -> Unit) {
        var exceptionMessage: String? = null
        var outputCode = OutputCode.Success
        val intentAction = Intent.ACTION_VIEW
        val type = "image/*"

        val intent = Intent()
        intent.action = intentAction
        intent.setDataAndType(uri, type)

        try {
            context.startActivity(intent)
        } catch (exception: Exception) {
            exceptionMessage = exception.message
            outputCode = OutputCode.Failure
        } finally {
            onTaskFinished(outputCode, exceptionMessage)
        }
    }

    fun storeBitmapToInternalStorage(fileName: String, bitmap: Bitmap, compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, compressionOutputQuality: Int = IntConstants.COMPRESSION_QUALITY_MAX) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            bitmap.compress(compressFormat, compressionOutputQuality, it)
            it.close()
        }
    }

    fun getBitmapFromUri(
        uri: Uri,
        onTaskFinished: (OutputCode, Bitmap?, String?) -> Unit) {
        var outputCode = OutputCode.Success
        var bitmap: Bitmap? = null
        var exceptionMessage: String? = null

        try {
            val contentResolver = context.applicationContext.contentResolver
            val parcelFileDescriptor: ParcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")!!
            val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor

            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
        } catch (ioException: IOException) {
            exceptionMessage = ioException.message
            outputCode = OutputCode.Failure
        } finally {
            onTaskFinished(outputCode, bitmap, exceptionMessage)
        }
    }
}
