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
import com.google.android.material.textfield.TextInputLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.tianscar.quickbitmap.BitmapEncoder
import org.beyka.tiffbitmapfactory.CompressionScheme
import org.beyka.tiffbitmapfactory.TiffSaver
import org.beyka.tiffbitmapfactory.TiffSaver.SaveOptions
import java.io.*


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
        compressionFormat: BitmapCompressFormat,
        onTaskFinished: (OutputCode, File, String?) -> Unit) {
        /** Thank you to to javatar on StackOverflow - quite a bit of the code here is based off of their solution.
         *
         * - [Link to javatar's profile](https://stackoverflow.com/users/2033223/javatar)
         * - [Original StackOverFlow post](https://stackoverflow.com/users/3571603/)
         * **/

        var exceptionMessage: String? = null
        var outputCode = OutputCode.Success
        val pathData = "image/jpeg"

        var outputName = "$projectTitle.${BitmapCompressFormatUtilities.getFormattedName(compressionFormat).lowercase()}"

        val directory: File? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            commonDocumentDirPath()
        } else {
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/" + context.getString(R.string.app_name))
        }

        directory?.mkdirs()

        var file = File(directory, outputName)

        val textInput: TextInputLayout =
            context.activity()?.layoutInflater?.inflate(R.layout.save_file_under_new_name_alert, context.activity()?.findViewById(android.R.id.content),false)
                as TextInputLayout

        fun createNewFile(file_: File, bitmap_: Bitmap = bitmap) {
            var bitmap2 = bitmap_

            when (compressionFormat) {
                BitmapCompressFormat.PNG, BitmapCompressFormat.JPEG, BitmapCompressFormat.WEBP, BitmapCompressFormat.WEBP_LOSSLESS -> {
                    try {
                        val outputStream = FileOutputStream(file_)

                        if (compressionFormat == BitmapCompressFormat.JPEG) {
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

                        bitmap2.compress(compressionFormat.correspondingEnum.invoke(), compressionOutputQuality, outputStream)
                        outputStream.close()
                    } catch (exception: Exception) {
                        exceptionMessage = exception.message
                        outputCode = OutputCode.Failure
                    } finally {
                        onTaskFinished(outputCode, file_, exceptionMessage)
                    }
                }

                BitmapCompressFormat.TIFF -> {
                    val options = SaveOptions()
                    options.inThrowException = true
                    options.compressionScheme = CompressionScheme.LZW

                    try {
                        TiffSaver.saveBitmap(file_.absolutePath, bitmap, options)
                    } catch (exception: Exception) {
                        exceptionMessage = exception.message
                        outputCode = OutputCode.Failure
                    } finally {
                        onTaskFinished(outputCode, file_, exceptionMessage)
                    }
                }

                else -> {
                    BitmapEncoder.encodeFile(file_, bitmap, true, BitmapEncoder.CompressFormat.BMP, IntConstants.COMPRESSION_QUALITY_MAX, object : BitmapEncoder.Callback {
                        override fun onCreateFailure() {
                            onTaskFinished(OutputCode.Failure, file_, context.getString(R.string.exception_failed_to_create_file))
                        }

                        override fun onCompressFailure() {
                            onTaskFinished(OutputCode.Failure, file_, context.getString(R.string.exception_failed_to_compress_file))
                        }

                        override fun onFileExists(isDirectory: Boolean) {

                        }

                        override fun onIOException(e: IOException?) {
                            onTaskFinished(OutputCode.Failure, file_, e?.message)
                        }

                        override fun onSuccess() {
                            onTaskFinished(OutputCode.Success, file_, exceptionMessage)
                        }
                    })
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
                .setNegativeButton(R.string.dialog_file_exists_negative_button_text) { _, _ ->
                    val innerAlertDialog = MaterialAlertDialogBuilder(context.activity()!!, R.style.ThemeOverlay_App_MaterialAlertDialog)
                        .setTitle(R.string.dialog_save_under_new_name_title)
                        .setView(textInput)
                        .setMessage(R.string.dialog_save_under_new_name_text)
                        .setPositiveButton(R.string.generic_ok) { _, _ ->
                            val input: String = textInput.editText?.text.toString()
                            outputName = "$input.${BitmapCompressFormatUtilities.getFormattedName(compressionFormat).lowercase()}"
                            file = File(directory, outputName)
                            createNewFile(file)
                        }
                        .setNegativeButton(R.string.generic_cancel, null)

                    innerAlertDialog.show()
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
