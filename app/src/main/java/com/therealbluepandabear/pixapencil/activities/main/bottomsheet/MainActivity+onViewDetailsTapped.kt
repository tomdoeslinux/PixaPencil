package com.therealbluepandabear.pixapencil.activities.main.bottomsheet

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.getNumberOfUniqueColors
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.models.PixelArt

fun MainActivity.extendedOnViewDetailsTapped(pixelArt: PixelArt) {
    val details: ConstraintLayout =
        activity()?.layoutInflater?.inflate(R.layout.project_details_alert_box, activity()?.findViewById(android.R.id.content),false)
                as ConstraintLayout

    details.findViewById<TextView>(R.id.projectDetailsAlertBox_width).text = pixelArt.width.toString()
    details.findViewById<TextView>(R.id.projectDetailsAlertBox_height).text = pixelArt.height.toString()
    details.findViewById<TextView>(R.id.projectDetailsAlertBox_colors).text = BitmapConverter.convertStringToBitmap(pixelArt.bitmap)?.getNumberOfUniqueColors().toString()
    details.findViewById<TextView>(R.id.projectDetailsAlertBox_created).text = pixelArt.dateCreated

    showDialog(
        getString(R.string.dialog_project_details_title),
        null,
        getString(R.string.generic_ok), { _, _ ->
        }, null, null, view = details, dimBackground = false
    )
}