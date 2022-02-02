package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.media.MediaScannerConnection
import android.os.Environment
import android.view.MotionEvent
import android.view.View
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.activities.canvas.projectTitle
import com.realtomjoney.pyxlmoose.activities.canvas.sharedPreferenceObject
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.listeners.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.models.*
import com.realtomjoney.pyxlmoose.utility.StringConstants
import java.io.File
import java.io.FileOutputStream

@SuppressLint("ViewConstructor")
class PixelGridView (context: Context, var spanCount: Int, private var isEmpty: Boolean) : View(context) {
    lateinit var pixelGridViewCanvas: Canvas
    lateinit var pixelGridViewBitmap: Bitmap

    var scaleWidth = 0f
    private var scaleHeight = 0f

    var prevX: Int? = null
    var prevY: Int? = null

    val bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    var currentBitmapAction: BitmapAction? = null

    var currentBrush: Brush? = null

    var pixelPerfectMode: Boolean = false

    lateinit var caller: CanvasFragmentListener

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        caller = context as CanvasFragmentListener

        if (::pixelGridViewBitmap.isInitialized) {
            pixelGridViewBitmap.recycle()
        }

        if (!isEmpty) {
            pixelGridViewBitmap = Bitmap.createBitmap(spanCount, spanCount, Bitmap.Config.ARGB_8888)
            pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
        }

        applyPixelPerfectValueFromPreference()
    }

    override fun dispatchTouchEvent(event: MotionEvent) = extendedDispatchTouchEvent(event)

    fun undo() = extendedUndo()

    fun clearCanvas() = extendedClearCanvas()

    fun getNumberOfUniqueColors() = extendedGetNumberOfUniqueColors()

    fun replacePixelsByColor(colorToFind: Int, colorToReplace: Int) = extendedReplacePixelsByColor(colorToFind, colorToReplace)

    fun applyBitmapFilter(lambda: (Int) -> Int) = extendedApplyBitmapFilter(lambda)

    fun overrideSetPixel(x: Int, y: Int, color: Int) = extendedOverrideSetPixel(x, y, color)

    fun replaceBitmap(newBitmap: Bitmap) = extendedReplaceBitmap(newBitmap)

    private fun applyPixelPerfectValueFromPreference() {
        if (sharedPreferenceObject.contains(StringConstants.SHARED_PREF_PIXEL_PERFECT)) {
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(StringConstants.SHARED_PREF_PIXEL_PERFECT, false)
        }
    }

    private fun calculateMatrix(bm: Bitmap, newHeight: Int, newWidth: Int): Matrix {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        this.scaleWidth = scaleWidth
        this.scaleHeight = scaleHeight

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        return matrix
    }

    enum class BitmapCompressionOutputCode {
        SUCCESS,
        FAILURE
    }

    // Thanks to https://stackoverflow.com/users/3571603/javatar on StackOverflow - quite a bit of the code is based off of their solution

    fun saveAsPNG() {
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
    
    override fun onDraw(canvas: Canvas) {
        if (::pixelGridViewBitmap.isInitialized) {
            canvas.drawBitmap(pixelGridViewBitmap, calculateMatrix(pixelGridViewBitmap, this.width, this.width), null)
        }
    }

}