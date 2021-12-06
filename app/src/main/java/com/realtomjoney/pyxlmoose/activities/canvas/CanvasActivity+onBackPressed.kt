package com.realtomjoney.pyxlmoose.activities.canvas

import android.content.Intent
import androidx.core.view.drawToBitmap
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.StringValues

fun CanvasActivity.extendedOnBackPressed() {
    if (index != -1) {
        AppData.db.pixelArtCreationsDao().apply {
            updatePixelArtCreationBitmap(BitmapConverter.convertBitmapToString(binding.fragmentHost.drawToBitmap()), currentPixelArtObj.objId)
            updatePixelArtCreationPixelData(JsonConverter.convertPixelListToJsonString(dataAsListOfPixels()), currentPixelArtObj.objId)
        }
    }
    if (currentFragmentInstance != null) this.navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.rootLayout, binding.colorPickerFragmentHost, StringValues.APP_NAME)
    startActivity(Intent(context, MainActivity::class.java))
}