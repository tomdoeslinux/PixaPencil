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
import com.google.android.material.textfield.TextInputLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.tianscar.quickbitmap.BitmapEncoder
import org.beyka.tiffbitmapfactory.CompressionScheme
import org.beyka.tiffbitmapfactory.TiffSaver
import org.beyka.tiffbitmapfactory.TiffSaver.SaveOptions
import java.io.File
import java.io.FileInputStream
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
                BitmapCompressFormat.PNG, BitmapCompressFormat.JPEG, BitmapCompressFormat.WEBP, BitmapCompressFormat.WEBP_LOSSLESS, BitmapCompressFormat.WEBP_LOSSY -> {
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
                    BitmapEncoder.encodeFile(file_, bitmap, true, BitmapEncoder.CompressFormat.BMP, 90, object : BitmapEncoder.Callback {
                        override fun onCreateFailure() {
                            onTaskFinished(OutputCode.Failure, file_, context.getString(R.string.exception_failed_to_create_file_in_code_str))
                        }

                        override fun onCompressFailure() {
                            onTaskFinished(OutputCode.Failure, file_, context.getString(R.string.exception_failed_to_compress_file_in_code_str))
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

        if (file.exists()) {
            context.activity()?.showDialog(
                context.getString(R.string.dialog_file_exists_title_in_code_str),
                context.getString(R.string.dialog_file_exists_message_in_code_str, outputName),
                context.getString(R.string.dialog_file_exists_positive_button_text_in_code_str), { _, _ ->
                    createNewFile(file)
                },
                context.getString(R.string.dialog_file_exists_negative_button_text_in_code_str), { _, _ ->
                context.activity()?.showDialog(
                    context.getString(R.string.dialog_save_under_new_name_title_in_code_str),
                    context.getString(R.string.dialog_save_under_new_name_text_in_code_str),
                    context.getString(R.string.generic_ok_in_code_str),
                    { _, _ ->
                        val input: String = textInput.editText?.text.toString()
                        outputName = "$input.${BitmapCompressFormatUtilities.getFormattedName(compressionFormat).lowercase()}"
                        file = File(directory, outputName)
                        createNewFile(file)
                    },
                    context.getString(R.string.generic_cancel_in_code_str), { _, _ -> },
                    textInput)
                })
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

    fun storeBitmapToInternalStorage(fileName: String, bitmap: Bitmap, compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, compressionOutputQuality: Int = 90) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            bitmap.compress(compressFormat, compressionOutputQuality, it)
            it.close()
        }
    }

    fun openBitmapFromInternalStorage(fileName: String): Bitmap {
        val directory = context.filesDir
        val file = File(directory, fileName)

        return BitmapFactory.decodeStream(FileInputStream(file))
    }

    fun deleteBitmapFromInternalStorage(fileName: String): Boolean {
        val directory = context.filesDir
        val file = File(directory, fileName)

        return file.delete()
    }
}
